package br.com.maquininha.cartao.maquininha.cartao.venda;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/vendas")
public class VendaController {

    @Autowired
    private VendaService vendaService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VendaDto cadastrarVenda(@RequestBody @Valid Venda venda) {
        Venda vendaModel = vendaService.cadastrarVenda(venda);

        return modelMapper.map(vendaModel, VendaDto.class);
    }

    @GetMapping("/{buscarVendas}")
    public Venda pesquisarVendaPorId(@PathVariable(name = "buscarVendas") int id) {
        Venda venda;
        try {
            venda = vendaService.buscarVendaPorId(id);
            return venda;
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarPeloID(@PathVariable int id){
        vendaService.deletarVenda(id);
    }

    @GetMapping("/extrato/{opcao}")
    public List<Venda> resumoDeVendaPorOpcao(@PathVariable(name = "opcao") Opcao opcao ){
        return vendaService.buscarVenda( opcao );
    }

}
