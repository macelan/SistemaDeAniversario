-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 13-Out-2020 às 14:55
-- Versão do servidor: 10.4.11-MariaDB
-- versão do PHP: 7.4.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `banco`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `alimento`
--

CREATE TABLE `alimento` (
  `idAlimento` int(11) NOT NULL,
  `nome` varchar(60) NOT NULL,
  `precoUnitario` double DEFAULT NULL,
  `tipoAlimento` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `alimento`
--

INSERT INTO `alimento` (`idAlimento`, `nome`, `precoUnitario`, `tipoAlimento`) VALUES
(1, 'Pastel Pequeneo', 0.25, 'Salgado frito'),
(2, 'Pastel grande', 0.5, 'Salgado frito'),
(3, 'risoles', 0.35, 'Salgado frito'),
(4, 'bolinha de queijo', 0.6, 'Salgado frito'),
(5, 'kibe', 0.35, 'Salgado frito'),
(6, 'empadas', 0.55, 'Salgado assado'),
(7, 'Pipoca de chocolate ou caramelada', 0.5, 'Doce'),
(8, 'Mini sanduíches', 1, 'Montados'),
(9, 'Mini cachorro quente', 0.9, 'Montados'),
(10, 'Mini pizza', 1.2, 'Montados'),
(11, 'Enroladinho de salsicha', 0.85, 'Montados'),
(12, 'Dadinho de tapioca', 0.75, 'Doce'),
(13, 'Brigadeiro', 0.45, 'Doce'),
(14, 'Beijinho', 0.35, 'Doce'),
(15, 'Quindim', 0.3, 'Doce'),
(16, 'Cupcakes', 0.65, 'Doce'),
(17, 'Trufas', 0.8, 'Doce'),
(18, 'Frutas banhadas no chocolate', 1.2, 'Doce'),
(19, 'Gelatina', 0.5, 'Doce'),
(20, 'Casadinho', 0.45, 'Doce'),
(21, 'Bala de coco', 0.25, 'Doce'),
(22, 'Bala de coco', 0.65, 'Doce');

-- --------------------------------------------------------

--
-- Estrutura da tabela `bebida`
--

CREATE TABLE `bebida` (
  `idBebida` int(11) NOT NULL,
  `nome` varchar(60) NOT NULL,
  `precoUnitario` double NOT NULL,
  `tipoBebida` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `bebida`
--

INSERT INTO `bebida` (`idBebida`, `nome`, `precoUnitario`, `tipoBebida`) VALUES
(1, 'Funada', 5.65, 'Refrigerante'),
(2, 'Cuba libre', 0.8, 'Prepadrados'),
(3, 'Margarita', 1.1, 'Prepadrados'),
(4, 'Daiquiri', 0.9, 'Prepadrados'),
(5, 'Coquetel de frutas com álcool', 1.5, 'Prepadrados'),
(6, 'Whisky Johnnie Walker Blue Label 750 ml', 800, 'Whisky'),
(7, 'Whisky Johnnie Walker Red Label 1000 ml', 99.9, 'Whisky'),
(8, 'Whisky Jack Daniel\'s 1000 ml', 139.9, 'Whisky'),
(9, 'Skol', 1.5, 'Cerveja'),
(10, 'Brahma', 1.1, 'Cerveja'),
(11, 'Itaipava', 0.85, 'Cerveja'),
(12, 'Devassa', 1.35, 'Cerveja'),
(13, 'Heineken', 1.85, 'Cerveja'),
(14, 'Antarctica', 1.1, 'Cerveja'),
(15, 'Coca-Cola 2 l', 7, 'Refrigerante'),
(16, 'Fanta', 5.5, 'Refrigerante'),
(17, 'Pepsi', 6.25, 'Refrigerante'),
(18, 'Guarana Kuat', 5.8, 'Refrigerante'),
(19, 'Dolly', 3.5, 'Refrigerante'),
(20, 'Guarana Antárctica', 6.7, 'Refrigerante'),
(21, 'velho barreiro', 8.5, 'Aguardente'),
(22, '51', 5.8, 'Aguardente'),
(23, 'Grappa Ouro', 109.9, 'Conhaque'),
(24, 'DOMECQ', 30.92, 'Conhaque'),
(25, 'Conhaque de Gengibre', 15.5, 'Conhaque'),
(26, 'Suco de limão siciliano com manjericão', 5.5, 'Suco'),
(27, 'abacaxi', 5.5, 'Suco'),
(28, 'Laranja', 9.8, 'Suco');

-- --------------------------------------------------------

--
-- Estrutura da tabela `cliente`
--

CREATE TABLE `cliente` (
  `idCliente` int(11) NOT NULL,
  `cpf` varchar(20) NOT NULL,
  `dataCadastro` date DEFAULT NULL,
  `endereco` varchar(80) NOT NULL,
  `nome` varchar(70) NOT NULL,
  `telefone` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `cliente`
--

INSERT INTO `cliente` (`idCliente`, `cpf`, `dataCadastro`, `endereco`, `nome`, `telefone`) VALUES
(2, '232.873.100-70', '2020-10-09', 'Rua: Das Cobras 110', 'Mariana da silva Campos', '18 3265-6598'),
(3, '637.759.330-93', '2020-10-09', 'Rua: Das Pinhas 25', 'Mario Zan Gonçalves', '18 3265-6598'),
(4, '945.745.710-20', '2020-10-09', 'Rua: Das Nações 5698', 'Mario Zan Gonçalves', '18 3265-6598'),
(5, '870.941.420-72', '2020-10-09', 'Rua: Dos Gaviões 177', 'João das Dores', '18 3265-6598'),
(6, '738.355.220-27', '2020-10-09', 'Rua: Da  Graça 15', 'Urso do Cabelo Duro', '18 3265-6598'),
(7, '310.250.700-03', '2020-10-09', 'Rua: Antonio Bandeira 56', 'Urso do Cabelo Duro', '18 3265-6598'),
(8, '830.919.540-05', '2020-10-09', 'Rua: Mariano Peixoto 598', 'Maria das Neves', '18 3265-6598'),
(9, '018.693.640-07', '2020-10-09', 'Rua: Rodolfo Mafra 15669', 'Pedelina das Neves rocha filho', '18 3265-6598'),
(10, '671.075.530-46', '2020-10-09', 'Avenida do Estado  236', 'Zé Colmeia', '18 3265-6598'),
(11, '607.367.680-84', '2020-10-09', 'Rua: Das Araras 130', 'João das Neves', '18 3265-6598');

-- --------------------------------------------------------

--
-- Estrutura da tabela `itemorcamentoalimento`
--

CREATE TABLE `itemorcamentoalimento` (
  `numeroItem` int(11) NOT NULL,
  `item_quantidade` int(11) DEFAULT NULL,
  `item_valor` double DEFAULT NULL,
  `idAlimento` int(11) DEFAULT NULL,
  `idOrcamento` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `itemorcamentoalimento`
--

INSERT INTO `itemorcamentoalimento` (`numeroItem`, `item_quantidade`, `item_valor`, `idAlimento`, `idOrcamento`) VALUES
(1, 25, 0.25, 1, 2),
(2, 25, 0.6, 4, 2),
(3, 25, 0.35, 3, 2),
(4, 25, 0.25, 1, 2),
(5, 100, 0.35, 3, 3),
(6, 150, 0.6, 4, 3),
(7, 50, 0.35, 5, 3);

-- --------------------------------------------------------

--
-- Estrutura da tabela `itemorcamentobebida`
--

CREATE TABLE `itemorcamentobebida` (
  `numeroItem` int(11) NOT NULL,
  `item_quantidade` int(11) DEFAULT NULL,
  `item_valor` double DEFAULT NULL,
  `idBebida` int(11) DEFAULT NULL,
  `idOrcamento` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `itemorcamentobebida`
--

INSERT INTO `itemorcamentobebida` (`numeroItem`, `item_quantidade`, `item_valor`, `idBebida`, `idOrcamento`) VALUES
(1, 1, 5.65, 1, 1),
(3, 4, 5.65, 1, 2),
(4, 2, 139.9, 8, 2),
(5, 5, 6.25, 17, 2),
(6, 3, 3.5, 19, 2),
(7, 1, 5.8, 22, 2),
(8, 10, 0.8, 2, 3),
(9, 14, 0.9, 4, 3);

-- --------------------------------------------------------

--
-- Estrutura da tabela `itemorcamentoutencilio`
--

CREATE TABLE `itemorcamentoutencilio` (
  `numeroItem` int(11) NOT NULL,
  `item_quantidade` int(11) DEFAULT NULL,
  `item_valor` double DEFAULT NULL,
  `idOrcamento` int(11) NOT NULL,
  `idUtensilio` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `itemorcamentoutencilio`
--

INSERT INTO `itemorcamentoutencilio` (`numeroItem`, `item_quantidade`, `item_valor`, `idOrcamento`, `idUtensilio`) VALUES
(1, 50, 0.6, 2, 3),
(2, 50, 0.6, 2, 4),
(3, 60, 0.45, 2, 5),
(4, 60, 0.25, 2, 7),
(5, 10, 5.5, 3, 1),
(6, 10, 0.6, 3, 4),
(7, 10, 0.5, 3, 6),
(8, 5, 5.5, 3, 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `orcamento`
--

CREATE TABLE `orcamento` (
  `idOrcamento` int(11) NOT NULL,
  `dataEvento` date DEFAULT NULL,
  `dataOrcamento` date DEFAULT NULL,
  `horaEvento` time DEFAULT NULL,
  `nomeDoAniversariante` varchar(70) DEFAULT NULL,
  `quantidadeAlimento` int(11) DEFAULT NULL,
  `quantidadeBebida` int(11) DEFAULT NULL,
  `quantidadeUtensilio` int(11) DEFAULT NULL,
  `status` varchar(70) DEFAULT NULL,
  `valorTotal` double DEFAULT NULL,
  `idCliente` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `orcamento`
--

INSERT INTO `orcamento` (`idOrcamento`, `dataEvento`, `dataOrcamento`, `horaEvento`, `nomeDoAniversariante`, `quantidadeAlimento`, `quantidadeBebida`, `quantidadeUtensilio`, `status`, `valorTotal`, `idCliente`) VALUES
(1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL),
(2, '2020-10-15', '2020-10-13', '15:15:00', 'Marisa', 100, 15, 220, 'Confirmado', 488.20000000000005, 2),
(3, '2020-10-14', '2020-10-13', '20:30:00', 'Catatau', 300, 24, 35, 'Pendente', 256.6, 10);

-- --------------------------------------------------------

--
-- Estrutura da tabela `usuario`
--

CREATE TABLE `usuario` (
  `idUsuario` int(11) NOT NULL,
  `login` varchar(80) NOT NULL,
  `nome` varchar(70) NOT NULL,
  `senha` varchar(80) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `usuario`
--

INSERT INTO `usuario` (`idUsuario`, `login`, `nome`, `senha`) VALUES
(1, 'scfa', 'ADMINISTRADOR', 'scfa'),
(2, 'Brucutu', 'Marildo da silva', 'brucu'),
(3, 'jn', 'Mariana da silva Campos', 'SenhaNova'),
(4, 'Tarzan', 'Mario Zan Gonçalves', 'mzg'),
(5, 'Chaves', 'João das Dores', 'dores'),
(6, 'Capitão', 'Capitão Caverna', 'Caverna'),
(7, 'Urso', 'Urso do Cabelo Duro', 'urso'),
(8, 'ze', 'Zé Colmeia', 'colmeia'),
(9, 'pedra	', 'Pedelina das Neves rocha filho', 'filho'),
(10, 'Zorro', 'João das Neves', '1234');

-- --------------------------------------------------------

--
-- Estrutura da tabela `utensilio`
--

CREATE TABLE `utensilio` (
  `idUtensilio` int(11) NOT NULL,
  `nome` varchar(60) NOT NULL,
  `precoUnitario` double NOT NULL,
  `tipoUtensilio` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `utensilio`
--

INSERT INTO `utensilio` (`idUtensilio`, `nome`, `precoUnitario`, `tipoUtensilio`) VALUES
(1, 'Toalha Pequena', 5.5, 'Toalha'),
(2, 'Toalha para mesa de centro', 10.5, 'Toalha'),
(3, 'Facas de mesa', 0.6, 'Talher'),
(4, 'Garfo', 0.6, 'Talher'),
(5, 'Prato  sobremesa', 0.45, 'Louça'),
(6, 'Prato grande', 0.5, 'Louça'),
(7, 'Pires sobremesa', 0.25, 'Louça'),
(8, 'Geardo Sobremesa', 0.15, 'Talher'),
(9, 'Cama elastica', 50, 'Brinquedo'),
(10, 'Piscina de Bolinha', 50, 'Brinquedo'),
(11, 'Escorregador', 60, 'Brinquedo'),
(12, 'Piscina De Bolinhas C/1000 Bolinhas 1,5 X 1,5m', 100, 'Brinquedo'),
(13, 'Escorregador Grande Divertido - Escada Vermelha e Rampa Azul', 50, 'Brinquedo');

--
-- Índices para tabelas despejadas
--

--
-- Índices para tabela `alimento`
--
ALTER TABLE `alimento`
  ADD PRIMARY KEY (`idAlimento`);

--
-- Índices para tabela `bebida`
--
ALTER TABLE `bebida`
  ADD PRIMARY KEY (`idBebida`);

--
-- Índices para tabela `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`idCliente`);

--
-- Índices para tabela `itemorcamentoalimento`
--
ALTER TABLE `itemorcamentoalimento`
  ADD PRIMARY KEY (`numeroItem`,`idOrcamento`),
  ADD KEY `FK_itemorcamentoalimento_idAlimento` (`idAlimento`),
  ADD KEY `FK_itemorcamentoalimento_idOrcamento` (`idOrcamento`);

--
-- Índices para tabela `itemorcamentobebida`
--
ALTER TABLE `itemorcamentobebida`
  ADD PRIMARY KEY (`numeroItem`,`idOrcamento`),
  ADD KEY `FK_itemorcamentobebida_idOrcamento` (`idOrcamento`),
  ADD KEY `FK_itemorcamentobebida_idBebida` (`idBebida`);

--
-- Índices para tabela `itemorcamentoutencilio`
--
ALTER TABLE `itemorcamentoutencilio`
  ADD PRIMARY KEY (`numeroItem`,`idOrcamento`),
  ADD KEY `FK_itemorcamentoutencilio_idUtensilio` (`idUtensilio`),
  ADD KEY `FK_itemorcamentoutencilio_idOrcamento` (`idOrcamento`);

--
-- Índices para tabela `orcamento`
--
ALTER TABLE `orcamento`
  ADD PRIMARY KEY (`idOrcamento`),
  ADD KEY `FK_Orcamento_idCliente` (`idCliente`);

--
-- Índices para tabela `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`idUsuario`),
  ADD UNIQUE KEY `login` (`login`);

--
-- Índices para tabela `utensilio`
--
ALTER TABLE `utensilio`
  ADD PRIMARY KEY (`idUtensilio`);

--
-- AUTO_INCREMENT de tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `alimento`
--
ALTER TABLE `alimento`
  MODIFY `idAlimento` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT de tabela `bebida`
--
ALTER TABLE `bebida`
  MODIFY `idBebida` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- AUTO_INCREMENT de tabela `cliente`
--
ALTER TABLE `cliente`
  MODIFY `idCliente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT de tabela `itemorcamentoalimento`
--
ALTER TABLE `itemorcamentoalimento`
  MODIFY `numeroItem` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de tabela `itemorcamentobebida`
--
ALTER TABLE `itemorcamentobebida`
  MODIFY `numeroItem` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de tabela `itemorcamentoutencilio`
--
ALTER TABLE `itemorcamentoutencilio`
  MODIFY `numeroItem` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de tabela `orcamento`
--
ALTER TABLE `orcamento`
  MODIFY `idOrcamento` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de tabela `usuario`
--
ALTER TABLE `usuario`
  MODIFY `idUsuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT de tabela `utensilio`
--
ALTER TABLE `utensilio`
  MODIFY `idUtensilio` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- Restrições para despejos de tabelas
--

--
-- Limitadores para a tabela `itemorcamentoalimento`
--
ALTER TABLE `itemorcamentoalimento`
  ADD CONSTRAINT `FK_itemorcamentoalimento_idAlimento` FOREIGN KEY (`idAlimento`) REFERENCES `alimento` (`idAlimento`),
  ADD CONSTRAINT `FK_itemorcamentoalimento_idOrcamento` FOREIGN KEY (`idOrcamento`) REFERENCES `orcamento` (`idOrcamento`);

--
-- Limitadores para a tabela `itemorcamentobebida`
--
ALTER TABLE `itemorcamentobebida`
  ADD CONSTRAINT `FK_itemorcamentobebida_idBebida` FOREIGN KEY (`idBebida`) REFERENCES `bebida` (`idBebida`),
  ADD CONSTRAINT `FK_itemorcamentobebida_idOrcamento` FOREIGN KEY (`idOrcamento`) REFERENCES `orcamento` (`idOrcamento`);

--
-- Limitadores para a tabela `itemorcamentoutencilio`
--
ALTER TABLE `itemorcamentoutencilio`
  ADD CONSTRAINT `FK_itemorcamentoutencilio_idOrcamento` FOREIGN KEY (`idOrcamento`) REFERENCES `orcamento` (`idOrcamento`),
  ADD CONSTRAINT `FK_itemorcamentoutencilio_idUtensilio` FOREIGN KEY (`idUtensilio`) REFERENCES `utensilio` (`idUtensilio`);

--
-- Limitadores para a tabela `orcamento`
--
ALTER TABLE `orcamento`
  ADD CONSTRAINT `FK_Orcamento_idCliente` FOREIGN KEY (`idCliente`) REFERENCES `cliente` (`idCliente`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
