package br.com.digio.adega.resource.v1.mapper;

import br.com.digio.adega.domain.entity.Cliente;
import br.com.digio.adega.resource.v1.dto.ClienteDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ClienteMapper {

    ClienteMapper INSTANCE = Mappers.getMapper(ClienteMapper.class);

    List<ClienteDTO> toDtoList(List<Cliente> clienteList);
}
