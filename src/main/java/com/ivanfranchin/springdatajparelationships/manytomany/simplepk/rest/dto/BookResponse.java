package com.ivanfranchin.springdatajparelationships.manytomany.simplepk.rest.dto;

import java.util.List;

public record BookResponse(Long id, String name, List<WriterRef> writers) {

  public record WriterRef(Long id, String name) {}
}
