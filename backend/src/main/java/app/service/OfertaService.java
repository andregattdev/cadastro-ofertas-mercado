package app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import app.model.Oferta;
import app.repository.OfertaRepository;

@Service
public class OfertaService {

    private final OfertaRepository ofertaRepository;

    public OfertaService(OfertaRepository ofertaRepository) {
        this.ofertaRepository = ofertaRepository;
    }

    /**
     * Listar todas as ofertas ativas.
     * @return todas as ofertas ativas.
     */
    public List<Oferta> listarOfertasAtivas() {
        return ofertaRepository.findByAtivaTrue();
    }

    /**
     * Listar todas as ofertas
     * @return todas as ofertas
     */
    public List<Oferta> listarTodasAsOfertas() {
        return ofertaRepository.findAll();
    }

    /**
     * Listar a oferta pelo ID.
     * @param id
     * @return Retorna a oferta pelo ID.
     */
    public Oferta buscarPorId(Long id) {
        return ofertaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Oferta não encontrada"));
    }

    /**
     * Salva a oferta.
     * @param oferta
     * @return Salva a oferta que o usuário colocou no sistema.
     */
    public Oferta salvar(Oferta oferta) {

        if (oferta.getPrecoPromocional()
                .compareTo(oferta.getPrecoOriginal()) > 0) {
            throw new IllegalArgumentException(
                    "O preço promocional não pode ser maior que o preço original");
        }

        if (oferta.getDataFim()
                .isBefore(oferta.getDataInicio())) {
            throw new IllegalArgumentException(
                    "A data fim não pode ser anterior à data início");
        }

        return ofertaRepository.save(oferta);
    }

    /**
     * Excluzão da oferta
     * @param id
     */
    public void excluir(Long id) {
        ofertaRepository.deleteById(id);
    }
}

