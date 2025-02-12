package org.wcs.myblog.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

public class ImageCreateDTO {
    @NotBlank(message = "URL cannot be empty")
    @URL(message = "URL must be valid")
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
