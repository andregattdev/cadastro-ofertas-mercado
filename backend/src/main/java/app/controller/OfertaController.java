package app.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import app.model.Oferta;
import app.service.OfertaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/ofertas")
@CrossOrigin(origins = "*")
public class OfertaController {

    private final OfertaService ofertaService;

    public OfertaController(OfertaService ofertaService) {
        this.ofertaService = ofertaService;
    }

    // Listar apenas ofertas ativas
    @GetMapping("/ativas")
    public List<Oferta> listarOfertasAtivas() {
        return ofertaService.listarOfertasAtivas();
    }

    // Listar todas as ofertas
    @GetMapping
    public List<Oferta> listarTodasAsOfertas() {
        return ofertaService.listarTodasAsOfertas();
    }

    // Buscar oferta por ID
    @GetMapping("/{id}")
    public Oferta buscarPorId(@PathVariable Long id) {
        return ofertaService.buscarPorId(id);
    }
    
    /**
     * Samvar a imagem do produto.
     * @param imagem
     * @return Retorna a imagem do produto.
     * @throws IOException
     */
    @PostMapping("/uploads")
    public String uploadImagem(
            @RequestParam("imagem") MultipartFile imagem)
            throws IOException {

        String nomeArquivo =
                System.currentTimeMillis()
                + "_" + imagem.getOriginalFilename();

        Path caminho = Paths.get("uploads", nomeArquivo);

        Files.createDirectories(caminho.getParent());

        Files.copy(imagem.getInputStream(), caminho);

        return "http://localhost:8080/api/ofertas/uploads/" + nomeArquivo;
    }

    // Cadastrar oferta
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Oferta criar(@RequestBody @Valid Oferta oferta) {
        return ofertaService.salvar(oferta);
    }

    // Atualizar oferta
    @PutMapping("/{id}")
    public Oferta atualizar(
            @PathVariable Long id,
            @RequestBody @Valid Oferta oferta) {

        Oferta ofertaExistente = ofertaService.buscarPorId(id);

        ofertaExistente.setProduto(oferta.getProduto());
        ofertaExistente.setDescricao(oferta.getDescricao());
        ofertaExistente.setPrecoOriginal(oferta.getPrecoOriginal());
        ofertaExistente.setPrecoPromocional(oferta.getPrecoPromocional());
        ofertaExistente.setDataInicio(oferta.getDataInicio());
        ofertaExistente.setDataFim(oferta.getDataFim());
        ofertaExistente.setImagemUrl(oferta.getImagemUrl());
        ofertaExistente.setAtiva(oferta.getAtiva());

        return ofertaService.salvar(ofertaExistente);
    }

    // Excluir oferta
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        ofertaService.excluir(id);
    }
}