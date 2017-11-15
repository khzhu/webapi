package uhn.pughlab.webapi.repositories;

import uhn.pughlab.webapi.persistence.Slide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Collection;

@RepositoryRestResource
public interface SlideRepository extends JpaRepository<Slide, Integer>{
    Collection<Slide> findByPatientId(String patientId);
}