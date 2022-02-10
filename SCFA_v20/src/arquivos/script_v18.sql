-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 30-Set-2020 às 03:08
-- Versão do servidor: 10.4.11-MariaDB
-- versão do PHP: 7.4.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";



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


