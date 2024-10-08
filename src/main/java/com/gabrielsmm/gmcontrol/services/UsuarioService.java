package com.gabrielsmm.gmcontrol.services;

import com.gabrielsmm.gmcontrol.dtos.UsuarioInsertRequestDTO;
import com.gabrielsmm.gmcontrol.dtos.UsuarioResponseDTO;
import com.gabrielsmm.gmcontrol.dtos.UsuarioUpdateRequestDTO;
import com.gabrielsmm.gmcontrol.entities.Usuario;
import com.gabrielsmm.gmcontrol.repositories.UsuarioRepository;
import com.gabrielsmm.gmcontrol.repositories.specifications.UsuarioSpecification;
import com.gabrielsmm.gmcontrol.security.UserSS;
import com.gabrielsmm.gmcontrol.services.exceptions.AuthorizationException;
import com.gabrielsmm.gmcontrol.services.exceptions.DataIntegrityException;
import com.gabrielsmm.gmcontrol.services.exceptions.ObjectNotFoundException;
import io.micrometer.common.util.StringUtils;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class UsuarioService {

    private UsuarioRepository usuarioRepository;

    private ModelMapper modelMapper;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public Usuario find(Long id) {
        Optional<Usuario> obj = usuarioRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! " +
                "Id: " + id + ", Tipo: " + Usuario.class.getName()));
    }

    public UsuarioResponseDTO findById(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado com o ID: " + id));

        return modelMapper.map(usuario, UsuarioResponseDTO.class);
    }

    public UsuarioResponseDTO findByNomeUsuario(String nomeUsuario) {
        Usuario obj = usuarioRepository.findByNomeUsuario(nomeUsuario);
        if (obj == null) {
            throw new ObjectNotFoundException("Objeto não encontrado! " +
                    "Usuário: " + nomeUsuario + ", Tipo: " + Usuario.class.getName());
        }
        return modelMapper.map(obj, UsuarioResponseDTO.class);
    }

    public UsuarioResponseDTO findByEmail(String email) {
        Usuario obj = usuarioRepository.findByEmail(email);
        if (obj == null) {
            throw new ObjectNotFoundException("Objeto não encontrado! " +
                    "Email: " + email + ", Tipo: " + Usuario.class.getName());
        }
        return modelMapper.map(obj, UsuarioResponseDTO.class);
    }

    public UsuarioResponseDTO findUsuarioLogado() {
        UserSS user = UserService.authenticated();
        if (user == null) {
            throw new AuthorizationException("Usuário não está logado");
        }
        return findById(user.getId());
    }

    public UsuarioResponseDTO insert(UsuarioInsertRequestDTO objDto) {
        Usuario usuario = modelMapper.map(objDto, Usuario.class);
        usuario.setId(null);
        usuario.setSenha(bCryptPasswordEncoder.encode(objDto.getSenha()));
        try {
            usuario = usuarioRepository.save(usuario);
            return modelMapper.map(usuario, UsuarioResponseDTO.class);
        } catch (Exception e) {
            throw new DataIntegrityException("Não foi possível inserir, erro de integridade de dados");
        }
    }

    public UsuarioResponseDTO update(Long id, UsuarioUpdateRequestDTO objDto) {
        Usuario usuario = find(id);
        modelMapper.map(objDto, usuario);
        if (StringUtils.isNotBlank(objDto.getSenha())) {
            usuario.setSenha(bCryptPasswordEncoder.encode(objDto.getSenha()));
        }
        usuario = usuarioRepository.save(usuario);
        return modelMapper.map(usuario, UsuarioResponseDTO.class);
    }

    public void delete(Long id) {
        find(id);
        try {
            usuarioRepository.deleteById(id);
        } catch (Exception e) {
            throw new DataIntegrityException("Não foi possível excluir, erro de integridade de dados");
        }
    }

    public Page<UsuarioResponseDTO> getListaPaginada(Integer pagina, Integer registrosPorPagina, String ordem, String direcao, String filtro) {
        PageRequest pageRequest = PageRequest.of(pagina, registrosPorPagina, Sort.Direction.valueOf(direcao), ordem);
        Page<Usuario> usuariosPage;

        if (StringUtils.isNotBlank(filtro)) {
            usuariosPage = usuarioRepository.findAll(UsuarioSpecification.contemTextoNosAtributos(filtro), pageRequest);
        } else {
            usuariosPage = usuarioRepository.findAll(pageRequest);
        }

        return usuariosPage.map(usuario -> modelMapper.map(usuario, UsuarioResponseDTO.class));
    }

}
