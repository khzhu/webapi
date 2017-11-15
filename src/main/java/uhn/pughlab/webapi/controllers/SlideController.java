package uhn.pughlab.webapi.controllers;

import uhn.pughlab.webapi.persistence.Slide;
import uhn.pughlab.webapi.services.SlideService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@Api(value="webapi", description="Operations pertaining to tissue images")
public class SlideController {

    private SlideService slideService;

    @Autowired
    public void setSlideService(SlideService slideService) {
        this.slideService = slideService;
    }

    @ApiOperation(value = "View a list of available slides",response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @RequestMapping(value = "/list-slides", method= RequestMethod.GET, produces = "application/json")
    public Iterable<Slide> list(Model model){
        Iterable<Slide> slideList = slideService.listAllSlides();
        return slideList;
    }

    @ApiOperation(value = "List image ids of available slides for a given patient",response = List.class)
    @RequestMapping(value = "/fetch-image-ids/{patientId}", method = RequestMethod.GET,  produces = "application/json")
    public List<Integer> listSlidesByPatientId(@PathVariable String patientId, Model model) {
        return this.slideService.listAllSlidesByPatientId(patientId)
                .stream().distinct()
                .map(Slide::getImageId)
                .collect(Collectors.toList());
    }

    @ApiOperation(value = "Search a slide with an ID",response = Slide.class)
    @RequestMapping(value = "/show-slide/{id}", method= RequestMethod.GET, produces = "application/json")
    public Slide showSlide(@PathVariable Integer id, Model model){
        Slide slide = slideService.getSlideById(id);
        return slide;
    }

    @ApiOperation(value = "Add a new slide")
    @RequestMapping(value = "/add-slide", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity saveSlide(@RequestBody Slide slide){
        slideService.saveSlide(slide);
        return new ResponseEntity("Slide saved successfully", HttpStatus.OK);
    }

    @ApiOperation(value = "Update a slide")
    @RequestMapping(value = "/update-slide/{id}", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity updateProduct(@PathVariable Integer id, @RequestBody Slide slide){
        Slide storedSlide = slideService.getSlideById(id);
        storedSlide.setImageId(slide.getImageId());
        storedSlide.setPatientId(slide.getPatientId());
        slideService.saveSlide(storedSlide);
        return new ResponseEntity("Slide updated successfully", HttpStatus.OK);
    }

    @ApiOperation(value = "Delete a slide")
    @RequestMapping(value="/delete-slide/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity delete(@PathVariable Integer id){
        slideService.deleteSlide(id);
        return new ResponseEntity("Slide deleted successfully", HttpStatus.OK);

    }

}
