package uhn.pughlab.webapi.persistence;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Slide {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes = "The database generated Slide ID")
    private Integer id;

    @NotBlank
    @Column(name="image_id")
    @ApiModelProperty(notes = "The ID of the tissue image stored in e-slide manager DB")
    private Integer imageId;

    @NotBlank
    @Column(name="patient_id")
    @ApiModelProperty(notes = "The study-specific patient ID")
    private String patientId;

    @Column(nullable = true, updatable = true, name = "last_modified")
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    @ApiModelProperty(notes="When tissue images last updated")
    private Date lastModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) { this.imageId = imageId; }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public Date getLastModified() { return lastModified; }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

}
