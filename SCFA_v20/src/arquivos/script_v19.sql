-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 30-Set-2020 às 03:08
-- Versão do servidor: 10.4.11-MariaDB
-- versão do PHP: 7.4.1


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
(1, 'Pastel Pequeneo', 0.25, 'Salgado frito');

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
(1, 'Funada', 5.65, 'Refrigerante');
(1, 'Funada', 5.65, 'Refrigerante');
(1, 'Funada', 5.65, 'Refrigerante');
(1, 'Funada', 5.65, 'Refrigerante');


-- --------------------------------------------------------

--
-- Estrutura da tabela `cliente`
--

CREATE TABLE `cliente` (
  `idCliente` int(11) NOT NULL,
  `cpf` varchar(12) NOT NULL,
  `dataCadastro` date DEFAULT NULL,
  `endereco` varchar(80) NOT NULL,
  `nome` varchar(70) NOT NULL,
  `telefone` varchar(14) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


--
-- Extraindo dados da tabela `cliente`
--

INSERT INTO `cliente` (`idCliente`, `cpf`, `dataCadastro`, `endereco`, `nome`, `telefone`) VALUES
(1, '607.367.680-84', '2020-10-09', 'Rua: Das Araras 130', 'João das Neves', '18 3265-6598'),
(2, '232.873.100-70', '2020-10-09', 'Rua: Das Cobras 110', 'Mariana da silva Campos', '18 3265-6598'),
(3, '637.759.330-93', '2020-10-09', 'Rua: Das Pinhas 25', 'Mario Zan Gonçalves', '18 3265-6598'),
(4, '945.745.710-20', '2020-10-09', 'Rua: Das Nações 5698', 'Mario Zan Gonçalves', '18 3265-6598'),
(5, '870.941.420-72', '2020-10-09', 'Rua: Dos Gaviões 177', 'João das Dores', '18 3265-6598'),
(6, '738.355.220-27', '2020-10-09', 'Rua: Da  Graça 15', 'Urso do Cabelo Duro', '18 3265-6598'),
(7, '310.250.700-03', '2020-10-09', 'Rua: Antonio Bandeira 56', 'Urso do Cabelo Duro', '18 3265-6598'),
(8, '830.919.540-05', '2020-10-09', 'Rua: Mariano Peixoto 598', 'Maria das Neves', '18 3265-6598'),
(9, '018.693.640-07', '2020-10-09', 'Rua: Rodolfo Mafra 15669', 'Pedelina das Neves rocha filho', '18 3265-6598'),
(10, '671.075.530-46', '2020-10-09', 'Avenida do Estado  236', 'Zé Colmeia', '18 3265-6598');

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
  `status` varchar(70) DEFAULT NULL,
  `valorTotal` float DEFAULT NULL,
  `idCliente` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `orcamento`
--


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
(1, 'Zorro', 'João das Neves', '1234'),
(2, 'Brucutu', 'Marildo da silva', 'brucu'),
(3, 'jn', 'Mariana da silva Campos', 'SenhaNova'),
(4, 'Tarzan', 'Mario Zan Gonçalves', 'mzg'),
(5, 'Chaves', 'João das Dores', 'dores'),
(6, 'Capitão', 'Capitão Caverna', 'Caverna'),
(7, 'Urso', 'Urso do Cabelo Duro', 'urso'),
(8, 'ze', 'Zé Colmeia', 'colmeia'),
(9, 'pedra	', 'Pedelina das Neves rocha filho', 'filho');


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
-- AUTO_INCREMENT de tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `alimento`
--
ALTER TABLE `alimento`
  MODIFY `idAlimento` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `bebida`
--
ALTER TABLE `bebida`
  MODIFY `idBebida` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `cliente`
--
ALTER TABLE `cliente`
  MODIFY `idCliente` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `orcamento`
--
ALTER TABLE `orcamento`
  MODIFY `idOrcamento` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de tabela `usuario`
--
ALTER TABLE `usuario`
  MODIFY `idUsuario` int(11) NOT NULL AUTO_INCREMENT;

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
-- Limitadores para a tabela `orcamento`
--
ALTER TABLE `orcamento`
  ADD CONSTRAINT `FK_Orcamento_idCliente` FOREIGN KEY (`idCliente`) REFERENCES `cliente` (`idCliente`);
COMMIT;


