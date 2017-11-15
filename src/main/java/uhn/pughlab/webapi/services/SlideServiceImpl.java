package uhn.pughlab.webapi.services;

import uhn.pughlab.webapi.persistence.Slide;
import uhn.pughlab.webapi.repositories.SlideRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class SlideServiceImpl implements SlideService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private SlideRepository slideRepository;

    @Autowired
    public void setSlideRepository(SlideRepository slideRepository) {
        this.slideRepository = slideRepository;
    }

    @Override
    public Iterable<Slide> listAllSlides() {
        logger.debug("listAllSlides called");
        return slideRepository.findAll();
    }

    @Override
    public Slide getSlideById(Integer id) {
        logger.debug("getSlideById called");
        return slideRepository.findOne(id);
    }

    @Override
    public Slide saveSlide(Slide slide) {
        logger.debug("saveSlide called");
        return slideRepository.save(slide);
    }

    @Override
    public void deleteSlide(Integer id) {
        logger.debug("deleteSlide called");
        slideRepository.delete(id);
    }

    @Override
    public Collection<Slide> listAllSlidesByPatientId(String patientId) {
        logger.debug("listAllSlidesByPatientId called");
        return slideRepository.findByPatientId(patientId);
    }
}
