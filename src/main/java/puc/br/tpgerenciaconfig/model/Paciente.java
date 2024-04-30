package puc.br.tpgerenciaconfig.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import puc.br.tpgerenciaconfig.utils.Constantes;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String nome;

    private String sobrenome;

    private char sexo;

    private LocalDate nascimento;

    private Byte idade;

    private Short altura;

    private Double peso;

    private String cpf;

    private Double imc;

    public Paciente(String nome, String sobrenome, char sexo, LocalDate nascimento, Short altura,
                    Double peso, String cpf) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.sexo = sexo;
        this.nascimento = nascimento;
        this.altura = altura;
        this.peso = peso;
        this.cpf = cpf;
        this.imc = _calcularIMC();
        this.idade = _calcularIdade();
    }

    public Double _obterPesoIdeal(){
        return (
                this.sexo == Constantes.sexo_masculino ?
                (Constantes.multiplicador_m * this.altura) - Constantes.reduzir_h :
                (Constantes.multiplicador_f * this.altura) - Constantes.reduzir_f
            );
    }

    public Double _calcularIMC(){
        return this.peso / (this.altura * this.altura);
    }

    public Byte _calcularIdade(){
        return (byte) this.nascimento.compareTo(LocalDate.now());
    }

    public String _obterSituacaoIMC(){

        if(this.imc < 17)
            return Constantes.abaixo_17;

        if(this.imc >= 17 && this.imc < 18.49)
            return Constantes.entre_17_18__49;

        if(this.imc >= 18.5 && this.imc < 24.99)
            return Constantes.entre_18__50_24__99;

        if(this.imc >= 25 && this.imc < 29.99)
            return Constantes.entre_25_29__99;

        if(this.imc >= 30 && this.imc < 34.99)
            return Constantes.entre_30_34__99;

        if(this.imc >= 35 && this.imc < 39.99)
            return Constantes.entre_35__39__99;

        return Constantes.acima_40;
    }

    public String _obterCpfOfuscado(){

        String aux = this.cpf;

        String digitos_aparecer = aux.substring(3,6);

        return "***." + digitos_aparecer + ".***-**";
    }
}
