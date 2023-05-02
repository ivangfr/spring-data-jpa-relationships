package com.ivanfranchin.springdatajparelationships.manytomany.simplepk.service;

import com.ivanfranchin.springdatajparelationships.manytomany.simplepk.exception.WriterNotFoundException;
import com.ivanfranchin.springdatajparelationships.manytomany.simplepk.model.Writer;
import com.ivanfranchin.springdatajparelationships.manytomany.simplepk.repository.WriterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class WriterServiceImpl implements WriterService {

    private final WriterRepository writerRepository;

    @Override
    public Writer validateAndGetWriter(Long id) {
        return writerRepository.findById(id).orElseThrow(() -> new WriterNotFoundException(id));
    }

    @Override
    public Writer saveWriter(Writer writer) {
        return writerRepository.save(writer);
    }

    @Override
    public void deleteWriter(Writer writer) {
        writerRepository.delete(writer);
    }
}
