package com.ivanfranchin.springdatajparelationships.manytomany.simplepk.service;

import com.ivanfranchin.springdatajparelationships.manytomany.simplepk.model.Writer;

public interface WriterService {

    Writer validateAndGetWriter(Long id);

    Writer saveWriter(Writer writer);

    void deleteWriter(Writer writer);
}
