
package com.ateam.services.demo.model.transaction;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "narrative",
    "comments",
    "tags",
    "images",
    "where"
})
public class Metadata_ {

    @JsonProperty("narrative")
    private String narrative;
    @JsonProperty("comments")
    private List<Comment> comments = null;
    @JsonProperty("tags")
    private List<Tag> tags = null;
    @JsonProperty("images")
    private List<Image> images = null;
    @JsonProperty("where")
    private Where where;

    @JsonProperty("narrative")
    public String getNarrative() {
        return narrative;
    }

    @JsonProperty("narrative")
    public void setNarrative(String narrative) {
        this.narrative = narrative;
    }

    @JsonProperty("comments")
    public List<Comment> getComments() {
        return comments;
    }

    @JsonProperty("comments")
    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @JsonProperty("tags")
    public List<Tag> getTags() {
        return tags;
    }

    @JsonProperty("tags")
    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    @JsonProperty("images")
    public List<Image> getImages() {
        return images;
    }

    @JsonProperty("images")
    public void setImages(List<Image> images) {
        this.images = images;
    }

    @JsonProperty("where")
    public Where getWhere() {
        return where;
    }

    @JsonProperty("where")
    public void setWhere(Where where) {
        this.where = where;
    }

}
