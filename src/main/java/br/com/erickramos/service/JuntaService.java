package br.com.erickramos.service;

import br.com.erickramos.repository.JuntaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JuntaService {

    @Autowired
    private JuntaRepository repository;

}
