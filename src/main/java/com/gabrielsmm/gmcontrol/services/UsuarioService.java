package com.gabrielsmm.gmcontrol.services;

import com.gabrielsmm.gmcontrol.dtos.UsuarioRequestDTO;
import com.gabrielsmm.gmcontrol.dtos.UsuarioResponseDTO;
import com.gabrielsmm.gmcontrol.entities.Usuario;
import com.gabrielsmm.gmcontrol.repositories.UsuarioRepository;
import com.gabrielsmm.gmcontrol.services.exceptions.DataIntegrityException;
import com.gabrielsmm.gmcontrol.services.exceptions.ObjectNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class UsuarioService {

    private UsuarioRepository usuarioRepository;

    private ModelMapper modelMapper;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UsuarioResponseDTO findById(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado com o ID: " + id));

        return modelMapper.map(usuario, UsuarioResponseDTO.class);
    }

    public UsuarioResponseDTO findByUsuario(String usuario) {
        Usuario obj = usuarioRepository.findByUsuario(usuario);
        if (obj == null) {
            throw new ObjectNotFoundException("Objeto não encontrado! " +
                    "Usuário: " + usuario + ", Tipo: " + Usuario.class.getName());
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

    public UsuarioResponseDTO insert(UsuarioRequestDTO objDto) {
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

    public UsuarioResponseDTO update(Long id, UsuarioRequestDTO objDto) {
        Usuario usuario = find(id);
        modelMapper.map(objDto, usuario);
        usuario.setSenha(bCryptPasswordEncoder.encode(objDto.getSenha()));
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

    private Usuario find(Long id) {
        Optional<Usuario> obj = usuarioRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! " +
                "Id: " + id + ", Tipo: " + Usuario.class.getName()));
    }

}
