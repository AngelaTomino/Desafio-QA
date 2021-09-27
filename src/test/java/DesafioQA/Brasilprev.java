package DesafioQA;

import io.restassured.specification.RequestSpecification;
import io.restassured.response.Response;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import java.io.*;



public class Brasilprev<baseURI, basePath, response> {
    @Test
    public void Brasilprev() {
        baseURI = "http://localhost:8080";
        basePath = "/pessoas";


        //Verificar o cadastro e retornar o código 400, pois o CPF já está cadastrado
        Response response;
        response = (Response) given()
                .contentType("application/json")
                .accept("application/json")
                .body("{\n" +
                        "\"codigo\": 0,\n" +
                        "\"nome\": \"Rafael Teixeira\",\n" +
                        "\"cpf\": \"12345678909\",\n" +
                        "\"enderecos\": [\n" +
                        "{\n" +
                        "\"logradouro\": \"Rua Alexandre Dumas\",\n" +
                        "\"numero\": 123,\n" +
                        "\"complemento\": \"Empresa\",\n" +
                        "\"bairro\": \"Chacara Santo Antonio\",\n" +
                        "\"cidade\": \"São Paulo\",\n" +
                        "\"estado\": \"SP\"\n" +
                        "}\n" +
                        "],\n" +
                        "\"telefones\": [\n" +
                        "{\n" +
                        "\"ddd\": \"11\",\n" +
                        "\"numero\": \"985388877\"\n" +
                        "}]\n" +
                        "}")
                .when()
                .post(baseURI + basePath)
                .then()
                .log().all()
                .extract();

        System.out.println("StatusCode = " + response.statusCode());
    }

    public void Telefonejacadastrado() {
        baseURI = "http://localhost:8080";
        basePath = "/pessoas";

        //Verificar o cadastro e retornar o código 400, pois o número do telefone já está cadastrado
        Response response;
        response = (Response) given()
                .contentType("application/json")
                .accept("application/json")
                .body("{\n" +
                        "\"codigo\": 0,\n" +
                        "\"nome\": \"Rafael Teixeira\",\n" +
                        "\"cpf\": \"70731500091\",\n" +
                        "\"enderecos\": [\n" +
                        "{\n" +
                        "\"logradouro\": \"Rua Alexandre Dumas\",\n" +
                        "\"numero\": 123,\n" +
                        "\"complemento\": \"Empresa\",\n" +
                        "\"bairro\": \"Chacara Santo Antonio\",\n" +
                        "\"cidade\": \"São Paulo\",\n" +
                        "\"estado\": \"SP\"\n" +
                        "}\n" +
                        "],\n" +
                        "\"telefones\": [\n" +
                        "{\n" +
                        "\"ddd\": \"11\",\n" +
                        "\"numero\": \"985388877\"\n" +
                        "}]\n" +
                        "}")
                .when()
                .get("/{11}/{985388877}")
                .then()
                .assertThat()
                .statusCode(400)
                .log().all();

        System.out.println("StatusCode = " + response.statusCode());
    }

    public void Telefoneinexistente() {
        baseURI = "http://localhost:8080";
        basePath = "/pessoas";

        //Procurar pessoa por telefone inexistente
        Response response;
        response = (Response) given()
                .contentType("application/json")
                .accept("application/json")
                .body("{\n" +
                        "\"codigo\": 0,\n" +
                        "\"nome\": \"Rafael Teixeira\",\n" +
                        "\"cpf\": \"12345678909\",\n" +
                        "\"enderecos\": [\n" +
                        "{\n" +
                        "\"logradouro\": \"Rua Alexandre Dumas\",\n" +
                        "\"numero\": 123,\n" +
                        "\"complemento\": \"Empresa\",\n" +
                        "\"bairro\": \"Chacara Santo Antonio\",\n" +
                        "\"cidade\": \"São Paulo\",\n" +
                        "\"estado\": \"SP\"\n" +
                        "}\n" +
                        "],\n" +
                        "\"telefones\": [\n" +
                        "{\n" +
                        "\"ddd\": \"11\",\n" +
                        "\"numero\": \"99019999\"\n" +
                        "}]\n" +
                        "}")
                .when()
                .get("/{11}/{985388876}")
                .then()
                .assertThat()
                .statusCode(400)
                .log().all();

        System.out.println("StatusCode = " + response.statusCode());
    }


    public void NovoCadastro() {
        baseURI = "http://localhost:8080";
        basePath = "/pessoas";

        //Cadastrar uma nova pessoa com sucesso
        Response response;
        response = (Response) given()
                .contentType("application/json")
                .accept("application/json")
                .body("{\n" +
                        "\"codigo\": 1,\n" +
                        "\"nome\": \"Angela Tomino\",\n" +
                        "\"cpf\": \"01234567890\",\n" +
                        "\"enderecos\": [\n" +
                        "{\n" +
                        "\"logradouro\": \"Rua das Flores\",\n" +
                        "\"numero\": 1,\n" +
                        "\"complemento\": \"Casa\",\n" +
                        "\"bairro\": \"Centroo\",\n" +
                        "\"cidade\": \"Sao Jose dos Campos\",\n" +
                        "\"estado\": \"SP\"\n" +
                        "}\n" +
                        "],\n" +
                        "\"telefones\": [\n" +
                        "{\n" +
                        "\"ddd\": \"12\",\n" +
                        "\"numero\": \"999999990\"\n" +
                        "}]\n" +
                        "}")
                .when()
                .post()
                .then()
                .assertThat()
                .statusCode(201)
                .log().all();

        System.out.println("StatusCode = " + response.statusCode());
    }

}

