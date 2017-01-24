package com.algaworks.comercial.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algaworks.comercial.model.Usuario;

public interface Usuarios extends JpaRepository<Usuario, Long> {

	Usuario findByLogin(String login);
}
