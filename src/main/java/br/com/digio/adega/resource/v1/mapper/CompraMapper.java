package br.com.digio.adega.resource.v1.mapper;

import br.com.digio.adega.domain.entity.Compra;
import br.com.digio.adega.resource.v1.dto.CompraDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CompraMapper {

    CompraMapper INSTANCE = Mappers.getMapper(CompraMapper.class);

    List<CompraDTO> toDtoList(List<Compra> compra);
}
