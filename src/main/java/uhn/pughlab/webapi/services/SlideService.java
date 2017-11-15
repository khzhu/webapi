package uhn.pughlab.webapi.services;

import uhn.pughlab.webapi.persistence.Slide;
import java.util.Collection;

public interface SlideService {
    Iterable<Slide> listAllSlides();

    Slide getSlideById(Integer id);

    Slide saveSlide(Slide slide);

    void deleteSlide(Integer id);

    Collection<Slide> listAllSlidesByPatientId(String patientId);

}