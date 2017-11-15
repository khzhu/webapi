package uhn.pughlab.webapi.bootstrap;

import uhn.pughlab.webapi.persistence.Slide;
import uhn.pughlab.webapi.repositories.SlideRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SpringJpaBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private SlideRepository slideRepository;


    private Logger log = Logger.getLogger(SpringJpaBootstrap.class);

    @Autowired
    public void setSlideRepository(SlideRepository slideRepository) {
        this.slideRepository = slideRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        //loadData();
    }

    private void loadData() {
        Slide slide1 = new Slide();
        slide1.setImageId(21);
        slide1.setPatientId("OCT-01-0254");
        slideRepository.save(slide1);

        log.info("Saved slide - id: " + slide1.getId());

        Slide slide2 = new Slide();
        slide2.setImageId(22);
        slide2.setPatientId("OCT-01-0270");
        slideRepository.save(slide2);

        log.info("Saved slide - id:" + slide2.getId());
    }


}



