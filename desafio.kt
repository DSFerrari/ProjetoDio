import kotlin.io.println as println

enum class nivel{BASICO,INTERMEDIARIO,DIFICIL }
data class ConteudoEducacional(val nome: String, val professor: String, val tempoDeDuracao: Int, val Nivel: nivel) {
    override fun toString(): String {
        return "Atividade: $nome, Professor: $professor, Tempo de Duração: $tempoDeDuracao minutos , nivel $Nivel "
    }
}

data class Formacao(val nomeFormacao: String, val assunto: MutableList<ConteudoEducacional>) {
    override fun toString(): String {
        val conteudosString = assunto.joinToString(separator = "\n") { it.toString() }
        return "Nome da Formação: $nomeFormacao\nConteúdos:\n$conteudosString"
    }
}
data class Usuario(val nomeUsuario: String?, val idadeUsuario: String?, val estado: String?){
    override fun toString(): String {
        return "Nome do Usuario: $nomeUsuario, Idade do Usuario: $idadeUsuario, Estado do Usuario: $estado "
    }
}
data class Usuarios(val Nome_formação: String?, val Usr: MutableList<Usuario>){
    override fun toString(): String {
        val usuariosString = Usr.joinToString(separator = "\n") { it.toString() }
        return "Nome da formação: $Nome_formação\n Usuarios:\n$usuariosString "
    }
}

val listaFormacoes = mutableListOf<Formacao>()
val Listainscritos = mutableListOf<Usuarios>()

fun main() {
    println("Bem-Vindo")
    println("Digite o que deseja fazer")

    while (true) {
        println("1. Para adicionar uma formação")
        println("2. Para visualizar as formações existentes")
        println("3. Para adicionar um usuário")
        println("4. Para visualizar o número de pessoas inscritas em uma formação")
        println("5. Para encerrar")

        val escolhaPrincipal: String? = readLine()

        when (escolhaPrincipal) {
            "1" -> adicionarFormacao()
            "2" -> visualizarFormacoes()
            "3" -> adicionarUsuario()
            "4" -> visualizarInscritos()
            "5" -> return
            else -> println("Opção inválida. Tente novamente.")
        }
    }
}

fun adicionarFormacao() {
    while (true) {
        println("Digite o nome da Formação")
        val nomeFormacao: String = readLine() ?: ""

        val assunto = mutableListOf<ConteudoEducacional>()

        val nomeFormacaojáExistente = nomeFormacao
        val formacaojáExistente = listaFormacoes.find { it.nomeFormacao == nomeFormacaojáExistente }

        while (true) {
            println("Digite o nome da Aula")
            val nome: String = readLine() ?: ""
            println("Digite o nome do Professor(a) responsável pela Aula")
            val professor: String = readLine() ?: ""
            println("Digite o tempo em minutos da Aula")
            val tempoDeDuracao: Int = readLine()?.toIntOrNull() ?: 0
            println("Digite o Nivel desse conteúdo : BASICO, INTERMEDIARIO ou DIFICIL")
            val nivelEscolha = readLine()
            val nivel = when (nivelEscolha?.toUpperCase()) {
                "BASICO" -> nivel.BASICO
                "INTERMEDIARIO" -> nivel.INTERMEDIARIO
                "DIFICIL" -> nivel.DIFICIL
                else -> {
                    println("Entrada inválida. Usando nível padrão: INTERMEDIARIO")
                    nivel.INTERMEDIARIO
                }
            }
            if (formacaojáExistente != null) {
                val conteudo = ConteudoEducacional(nome, professor, tempoDeDuracao, nivel)
                formacaojáExistente.assunto.add(conteudo)
            }
            else {
                val conteudo = ConteudoEducacional(nome, professor, tempoDeDuracao, nivel)
                assunto.add(conteudo)
            }

            println("Deseja adicionar mais alguma matéria nesse curso ? (Sim/Não)")
            val escolhaAdicionarMateria: String = readLine()?.toLowerCase() ?: ""
            if (escolhaAdicionarMateria == "não") {
                break
            }
        }

        val formacao = Formacao(nomeFormacao, assunto)
        println("A formação é:")
        println(formacao)

        listaFormacoes.add(formacao)
        println("Deseja adicionar mais alguma formação ? (Sim/Não)")
        val escolhaAdicionarFormacao: String = readLine()?.toLowerCase() ?: ""
        if (escolhaAdicionarFormacao == "não") {
            break
        }
    }
}
fun visualizarFormacoes() {
    if (listaFormacoes.isEmpty()) {
        println("Voce não tem formações criadas")
        println("Crie uma formação primeiro para depois poder visualizar formações existentes")
        return
    }
    while (true) {
        println("Se deseja visualizar os usuários inscritos em uma formação específica digite 1, se for todas as formações digite 2")
        val opçãoescolhavisualizaçãoformação: String? = readLine()
        when (opçãoescolhavisualizaçãoformação) {

            "1" -> {
                println("Digite o nome da formação específica:")
                val nomeFormacaoespecif = readLine()
                val formacaoEncontrada = listaFormacoes.find { it.nomeFormacao == nomeFormacaoespecif }

                if (formacaoEncontrada != null) {
                    val conteudosdaformação = formacaoEncontrada.assunto
                            println("Conteudos da formação $nomeFormacaoespecif: $conteudosdaformação ")
                } else {
                    println("Formação não encontrada.")
                }
                break
            }

            "2" -> {
                println("Formações existentes:")
                for (formacao in listaFormacoes) {
                    println(formacao.toString())
                }
                return
            }
        }
    }
}

fun adicionarUsuario() {
    if (listaFormacoes.isEmpty()) {
        println("Voce não tem formações criadas")
        println("Crie uma formação primeiro para depois poder cadastrar alunos")
        return
    }
    loopPrincipal@ while (true) {
        println("Digite o Nome Da Formação")
        val Nome_formação: String? = readLine()
        while (true) {
            val FormaçãoExistencia = listaFormacoes.any { it.nomeFormacao == Nome_formação }
            if (FormaçãoExistencia) {
                break
            } else {
                println("Essa formação Não foi criada, digite 1 para tentar novamente ou digite 2 para voltar ao menu principal")
                val Opção_voltar: String? = readLine()
                when (Opção_voltar) {
                    "1" -> continue@loopPrincipal

                    "2" -> {
                        return
                    }

                    else -> {
                        println("opção invalida digite novamente")
                    }
                }
            }
        }
        val nomeFormacaoExistente = Nome_formação
        val formacaoExistente = Listainscritos.find { it.Nome_formação == nomeFormacaoExistente }

            val Usr = mutableListOf<Usuario>()
            while (true) {
                println("Digite o nome do usuario :")
                val nomeUsuario: String? = readLine()
                println("Digite a idade do usuario :")
                val idadeUsuario: String? = readLine()
                println("Digite o estado do usuario")
                val estado: String? = readLine()
                if (formacaoExistente != null) {
                    val User = Usuario(nomeUsuario, idadeUsuario, estado)
                    formacaoExistente.Usr.add(User)
                }
                else {
                    val User = Usuario(nomeUsuario, idadeUsuario, estado)
                    Usr.add(User)
                }
                println("Deseja adicionar mais algum usuario nessa formação ?")
                val EscolhaUser: String? = readLine()
                if (EscolhaUser == "não") {
                    break
                }
            }
            val usuarios = Usuarios(Nome_formação, Usr)
            Listainscritos.add(usuarios)
            println("Deseja cadastrar em outra formação ?")
            val opçãocadastro: String? = readLine()
            if (opçãocadastro == "não") {
                break
            }
        }
    }
fun visualizarInscritos() {
    if (Listainscritos.isEmpty()) {
        println("Voce não tem usuarios cadastrados")
        println("Cadastre usuarios primeiro para poder visualiza-los depois")
        return
    }
    while (true) {
        println("Se deseja visualizar os usuários inscritos em uma formação específica digite 1, se for todas as formações digite 2")
        val opçãoescolhavisualização: String? = readLine()
        when (opçãoescolhavisualização) {

            "1" -> {
                println("Digite o nome da formação específica:")
                val nomeFormacao = readLine()
                val formacaoEncontrada = Listainscritos.find { it.Nome_formação == nomeFormacao }

                if (formacaoEncontrada != null) {
                    val pessoasInscritas = formacaoEncontrada.Usr
                    println("Pessoas inscritas na formação $nomeFormacao: $pessoasInscritas")
                } else {
                    println("Formação não encontrada.")
                }
                break
            }


            "2" -> {
                println("Formações existentes:")
                for (usuarios in Listainscritos) {
                    println(usuarios)
                }
                break
            }

            else -> {
                println("Opção invalída")
                println("Tente novamente")
            }
        }
    }
}
