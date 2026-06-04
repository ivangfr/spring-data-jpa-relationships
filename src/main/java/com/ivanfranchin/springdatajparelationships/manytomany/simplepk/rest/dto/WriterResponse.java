package com.ivanfranchin.springdatajparelationships.manytomany.simplepk.rest.dto;

import java.util.List;

public record WriterResponse(Long id, String name, List<BookRef> books) {

  public record BookRef(Long id, String name) {}
}
