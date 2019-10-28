package br.com.fiap.netflix.consumer.controller;

import br.com.fiap.netflix.consumer.entity.Curtida;
import br.com.fiap.netflix.consumer.repository.CurtidaRepository;
import com.google.gson.Gson;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurtidaController {

    @Autowired
    CurtidaRepository curtidaRepository;

    @ApiOperation(
            value="Adiciona likes a um Movie ",
            response= String.class,
            notes="Essa operação Adiciona likes a um Movie")
    @ApiResponses(value= {
            @ApiResponse(
                    code=200,
                    message="Inserção feita com sucesso",
                    response= String.class
            ),
            @ApiResponse(
                    code=500,
                    message="Erro",
                    response=String.class
            )

    })
    @PostMapping("/")
    public String insertlike(@RequestBody Curtida id){

        return new Gson().toJson(curtidaRepository.save(id));
    }


    @ApiOperation(
            value="Adiciona likes a um Movie ",
            response= String.class,
            notes="Essa operação Adiciona likes a um Movie")
    @ApiResponses(value= {
            @ApiResponse(
                    code=200,
                    message="Inserção feita com sucesso",
                    response= String.class
            ),
            @ApiResponse(
                    code=500,
                    message="Erro",
                    response=String.class
            )

    })
    @PostMapping("/like")
    public void updatelike(@RequestBody Long id){

       Curtida curtida =  curtidaRepository.findByIdMovie(id);

       curtida.setCurtida(curtida.getCurtida()+1);

       curtidaRepository.save(curtida);

    }





}
