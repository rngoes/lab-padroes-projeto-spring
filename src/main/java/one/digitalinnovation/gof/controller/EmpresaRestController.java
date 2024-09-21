package one.digitalinnovation.gof.controller;

import one.digitalinnovation.gof.model.Cliente;
import one.digitalinnovation.gof.model.Empresa;
import one.digitalinnovation.gof.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("empresas")
public class EmpresaRestController {

    @Autowired
    private EmpresaService empresaService;

    @GetMapping
    public ResponseEntity<Iterable<Empresa>> buscarTodos() {
        return ResponseEntity.ok(empresaService.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empresa> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(empresaService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Empresa> inserir(@RequestBody Empresa empresa) {
        empresaService.inserir(empresa);
        return ResponseEntity.ok(empresa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Empresa> atualizar(@PathVariable Long id, @RequestBody Empresa empresa) {
        empresaService.atualizar(id, empresa);
        return ResponseEntity.ok(empresa);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        empresaService.deletar(id);
        return ResponseEntity.ok().build();
    }
}
