-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 18-Set-2020 às 00:29

create database banco2;
use banco2;

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
(1, 'Pastel ', 0.4, 'Salgado frito'),
(2, 'casadinho', 0.25, 'Doce');

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
(1, 'Whisky Johnnie Walker Blue Label Ghost Glenury Royal 750ml', 2000, 'Whisky'),
(2, 'WHISKEY BULLEIT BOURBON - 750ML', 139.9, 'Whisky');

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
(1, '1132356548', '2020-09-17', 'Rua: das araraas -185 - centro - São Paulo', 'Maria Joaquina', '111236985'),
(2, '113665449978', '2020-09-17', 'Rua: Gardon palhaço,15699 - Vila Matilde - Osasco', 'João das gotas', '1113669526');

-- --------------------------------------------------------

--
-- Estrutura da tabela `orcamento`
--

CREATE TABLE `orcamento` (
  `idOrcamento` int(11) NOT NULL,
  `dataEvento` date DEFAULT NULL,
  `dataOrcamento` date DEFAULT NULL,
  `horaEvento` time DEFAULT NULL,
  `nomeDoAniversariante` varchar(70) NOT NULL,
  `status` tinyint(1) DEFAULT 0,
  `valor` float NOT NULL,
  `idCliente` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estrutura da tabela `orcamento_alimento`
--

CREATE TABLE `orcamento_alimento` (
  `alimento_idAlimento` int(11) NOT NULL,
  `orcamento_idOrcamento` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estrutura da tabela `orcamento_bebida`
--

CREATE TABLE `orcamento_bebida` (
  `bebida_idBebida` int(11) NOT NULL,
  `orcamento_idOrcamento` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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
(1, 'MAO', 'Usuário 1', '1236'),
(2, 'Malicia', 'Usuário 2', '123456');

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
-- Índices para tabela `orcamento`
--
ALTER TABLE `orcamento`
  ADD PRIMARY KEY (`idOrcamento`),
  ADD KEY `FK_Orcamento_idCliente` (`idCliente`);

--
-- Índices para tabela `orcamento_alimento`
--
ALTER TABLE `orcamento_alimento`
  ADD PRIMARY KEY (`alimento_idAlimento`,`orcamento_idOrcamento`),
  ADD KEY `FK_ORCAMENTO_ALIMENTO_orcamento_idOrcamento` (`orcamento_idOrcamento`);

--
-- Índices para tabela `orcamento_bebida`
--
ALTER TABLE `orcamento_bebida`
  ADD PRIMARY KEY (`bebida_idBebida`,`orcamento_idOrcamento`),
  ADD KEY `FK_ORCAMENTO_BEBIDA_orcamento_idOrcamento` (`orcamento_idOrcamento`);

--
-- Índices para tabela `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`idUsuario`);

--
-- AUTO_INCREMENT de tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `alimento`
--
ALTER TABLE `alimento`
  MODIFY `idAlimento` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de tabela `bebida`
--
ALTER TABLE `bebida`
  MODIFY `idBebida` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de tabela `cliente`
--
ALTER TABLE `cliente`
  MODIFY `idCliente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de tabela `orcamento`
--
ALTER TABLE `orcamento`
  MODIFY `idOrcamento` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `usuario`
--
ALTER TABLE `usuario`
  MODIFY `idUsuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Restrições para despejos de tabelas
--

--
-- Limitadores para a tabela `orcamento`
--
ALTER TABLE `orcamento`
  ADD CONSTRAINT `FK_Orcamento_idCliente` FOREIGN KEY (`idCliente`) REFERENCES `cliente` (`idCliente`);

--
-- Limitadores para a tabela `orcamento_alimento`
--
ALTER TABLE `orcamento_alimento`
  ADD CONSTRAINT `FK_ORCAMENTO_ALIMENTO_alimento_idAlimento` FOREIGN KEY (`alimento_idAlimento`) REFERENCES `alimento` (`idAlimento`),
  ADD CONSTRAINT `FK_ORCAMENTO_ALIMENTO_orcamento_idOrcamento` FOREIGN KEY (`orcamento_idOrcamento`) REFERENCES `orcamento` (`idOrcamento`);

--
-- Limitadores para a tabela `orcamento_bebida`
--
ALTER TABLE `orcamento_bebida`
  ADD CONSTRAINT `FK_ORCAMENTO_BEBIDA_bebida_idBebida` FOREIGN KEY (`bebida_idBebida`) REFERENCES `bebida` (`idBebida`),
  ADD CONSTRAINT `FK_ORCAMENTO_BEBIDA_orcamento_idOrcamento` FOREIGN KEY (`orcamento_idOrcamento`) REFERENCES `orcamento` (`idOrcamento`);
COMMIT;

