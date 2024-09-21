package one.digitalinnovation.gof.service;

import one.digitalinnovation.gof.model.Empresa;

public interface EmpresaService {

    Iterable<Empresa> buscarTodos();

    Empresa buscarPorId(Long id);

    void inserir(Empresa empresa);

    void atualizar (Long id, Empresa empresa);

    void deletar (Long id);

}
