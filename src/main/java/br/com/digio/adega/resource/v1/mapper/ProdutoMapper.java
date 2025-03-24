package br.com.digio.adega.resource.v1.mapper;

import br.com.digio.adega.domain.entity.Produto;
import br.com.digio.adega.resource.v1.dto.ProdutoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProdutoMapper {

    ProdutoMapper INSTANCE = Mappers.getMapper(ProdutoMapper.class);

    ProdutoDTO toDto(Produto produto);
}
