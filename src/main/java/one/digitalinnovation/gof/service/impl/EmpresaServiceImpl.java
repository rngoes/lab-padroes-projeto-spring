package one.digitalinnovation.gof.service.impl;

import one.digitalinnovation.gof.model.Empresa;
import one.digitalinnovation.gof.model.EmpresaRepository;
import one.digitalinnovation.gof.model.Endereco;
import one.digitalinnovation.gof.model.EnderecoRepository;
import one.digitalinnovation.gof.service.EmpresaService;
import one.digitalinnovation.gof.service.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmpresaServiceImpl implements EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private ViaCepService viaCepService;

    @Override
    public Iterable<Empresa> buscarTodos() {
        return empresaRepository.findAll();
    }

    @Override
    public Empresa buscarPorId(Long id) {
       Optional<Empresa> empresa = empresaRepository.findById(id);
       return empresa.get();
    }

    @Override
    public void inserir(Empresa empresa) {
        salvarEmpresaComCep(empresa);
    }

    @Override
    public void atualizar(Long id, Empresa empresa) {
        Optional<Empresa> empresaBd = empresaRepository.findById(id);
        if (empresaBd.isPresent()) {
            salvarEmpresaComCep(empresa);
        }

    }

    @Override
    public void deletar(Long id) {
        empresaRepository.deleteById(id);
    }

    private void salvarEmpresaComCep (Empresa empresa) {
        String cep = empresa.getEndereco().getCep();
        Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
            Endereco novoEndereco = viaCepService.consultarCep(cep);
            enderecoRepository.save(novoEndereco);
            return novoEndereco;
        });
        empresa.setEndereco(endereco);
        empresaRepository.save(empresa);
    }
}
