package io.github.pi.gerencestacionamento.service;

import io.github.pi.gerencestacionamento.Exception.ParametroInvalidoException;
import io.github.pi.gerencestacionamento.Exception.DesativadoException;
import io.github.pi.gerencestacionamento.Exception.NaoEncontradoException;
import io.github.pi.gerencestacionamento.domain.client.Enum.TipoPlanoEnum;
import io.github.pi.gerencestacionamento.domain.client.entity.Usuario;
import io.github.pi.gerencestacionamento.domain.movimentoVagas.Enum.EstadoMovimentacaoEnum;
import io.github.pi.gerencestacionamento.domain.movimentoVagas.dto.MovimentoVagaDTOConverter;
import io.github.pi.gerencestacionamento.domain.movimentoVagas.dto.MovimentoVagasResponseDTO;
import io.github.pi.gerencestacionamento.domain.movimentoVagas.entity.MovimentoVaga;
import io.github.pi.gerencestacionamento.domain.vaga.dto.VagaResponseDTO;
import io.github.pi.gerencestacionamento.domain.vaga.entity.Vaga;
import io.github.pi.gerencestacionamento.repository.ClientRepository;
import io.github.pi.gerencestacionamento.repository.MovesParkingSpacesRepository;
import io.github.pi.gerencestacionamento.repository.ParkingSpacesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ParkingSpacesService {
    @Autowired
    private ParkingSpacesRepository parkingSpacesRepository;

    @Autowired
    private MovesParkingSpacesRepository movesParkingSpacesRepository;

    @Autowired
    private ClientRepository clientRepository;

    public List<MovimentoVagasResponseDTO> findAllMoves(){
        return movesParkingSpacesRepository.findAll()
                .stream()
                .map(MovimentoVagasResponseDTO::fromMovimentoVaga)
                .collect(Collectors.toList());
    }

    public List<VagaResponseDTO> findAll(){
        return parkingSpacesRepository.findAll()
                .stream()
                .map(VagaResponseDTO::fromVaga)
                .collect(Collectors.toList());
    }

    public Optional<MovimentoVagaDTOConverter> findOneMove(int id) {
        if(id <= 0) throw new ParametroInvalidoException("Verifique o campo id da movimentação!");

        Optional<MovimentoVaga> respDTO = movesParkingSpacesRepository.findById(id);
        if(respDTO.isEmpty()) throw new NaoEncontradoException("Ops, essa vaga não foi encontrada!");

        return Optional.of(MovimentoVagaDTOConverter.fromMovimentoVagas(respDTO.get()));
    }

    public Optional<VagaResponseDTO> findOne(int id) {
        if(id <= 0) throw new ParametroInvalidoException("Verifique o campo id da vaga!");

        Optional<Vaga> respDTO = parkingSpacesRepository.findById(id);
        if(respDTO.isEmpty()) throw new NaoEncontradoException("Ops, essa vaga não foi encontrada!");
        if(respDTO.get().isEstadoVaga()) throw new DesativadoException("Essa vaga foi desativada!");

        return Optional.of(VagaResponseDTO.fromVaga(respDTO.get()));
    }

    public List<VagaResponseDTO> findAllByUser(int id) {
        if(id <= 0) throw new ParametroInvalidoException("Verifique o campo id da vaga!");

        List<MovimentoVagasResponseDTO> vagas = movesParkingSpacesRepository.findAllByFkUsuario(id)
                .stream()
                .map(MovimentoVagasResponseDTO::fromMovimentoVaga)
                .collect(Collectors.toList());

        List<Vaga> respDTO = new ArrayList<Vaga>();
        for(MovimentoVagasResponseDTO vaga : vagas) {
            Optional<Vaga> findVaga = parkingSpacesRepository.findById(vaga.getFkVaga());
            if(!findVaga.isEmpty())
                respDTO.add(findVaga.get());
        }

        if(respDTO.isEmpty()) throw new NaoEncontradoException("Ops, nenhuma vaga foi encontrada!");

        return respDTO.stream().map(VagaResponseDTO::fromVaga).collect(Collectors.toList());
    }

    public boolean create(MovimentoVaga movimentoVaga) {
        if(movimentoVaga == null) throw new ParametroInvalidoException("Verifique se todos os campos foram preechidos!");

        System.out.println(movimentoVaga.getFkVaga());
        Optional<Vaga> vaga = parkingSpacesRepository.findById(movimentoVaga.getFkVaga());
        boolean changeStatus = false;
        if(!vaga.isEmpty()) {
            if(!vaga.get().isEstadoVaga())
                throw new DesativadoException("Essa vaga não está disponivel!");

            vaga.get().setEstadoVaga(false);
            changeStatus = updateSpaceOne(vaga.get());
        }

        if(!changeStatus)
            throw new NaoEncontradoException("Ops, essa vaga não foi encontrada!");

        movimentoVaga.setDataInclusao(LocalDate.now());
        movimentoVaga.setEstadoMovimentacao(EstadoMovimentacaoEnum.ATIVO);
        movesParkingSpacesRepository.save(movimentoVaga);
        return true;
    }

    public boolean updateSpaceOne(Vaga vaga) {
        if(vaga.getIdVaga() <= 0) throw new ParametroInvalidoException("Verifique o campo id da vaga!");

        parkingSpacesRepository.save(vaga);
        return true;
    }

    public boolean updateOne(MovimentoVaga movimentoVagas) {
        if(movimentoVagas.getId() <= 0) throw new ParametroInvalidoException("Verifique o campo id do MovimentoVagas!");
        if(!movesParkingSpacesRepository.existsById(movimentoVagas.getId())) throw new NaoEncontradoException("Ops, esse movimentação na vaga não foi encontrado!");

        movesParkingSpacesRepository.save(movimentoVagas);
        return true;
    }

    public boolean calcPayment(MovimentoVaga movimentoVagas) {
        if (movimentoVagas.getId() <= 0) {
            throw new ParametroInvalidoException("Verifique o campo id do MovimentoVagas!");
        }
        MovimentoVagasResponseDTO movimento = MovimentoVagaDTOConverter.fromMovimentoVagaDTOConverter(findOneMove(movimentoVagas.getId()).get());
        if(movimento.getEstadoMovimentacao() == EstadoMovimentacaoEnum.PENDENTE)
            throw new DesativadoException("Essa movimentação está 'PENDENTE', efetue o pagamento para desbloquea-la!");

        movimento.setDataAlteracao(movimentoVagas.getDataAlteracao());
        Usuario usuario = clientRepository.findById(movimento.getFkUsuario())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        float valorPlano = usuario.getTipoPlanoEnum() == TipoPlanoEnum.DIARIO ? 140 : 10;
        if (movimento.getDataInclusao() == null || movimento.getDataAlteracao() == null) {
            throw new IllegalStateException("Datas de inclusão/alteração não podem ser nulas!");
        }

        long diasAlocado = ChronoUnit.DAYS.between(
                movimento.getDataInclusao(),
                movimento.getDataAlteracao()
        );
        float valorTotal = diasAlocado * valorPlano;

        movimento.setEstadoMovimentacao(EstadoMovimentacaoEnum.PENDENTE);
        movimento.setValorVaga(valorTotal);
        movesParkingSpacesRepository.save(MovimentoVagasResponseDTO.fromMovimentoVaga(movimento));
        return true;
    }

    public boolean DeleteOne(int id) {
        if(id <= 0) throw new ParametroInvalidoException("Verifique o campo id da movimentação da vaga!");
        if(!parkingSpacesRepository.existsById(id)) throw new NaoEncontradoException("Ops, essa movimentação não foi encontrada!");

        parkingSpacesRepository.deleteById(id);
        return true;
    }
}
