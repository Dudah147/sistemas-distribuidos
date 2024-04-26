-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 26/04/2024 às 22:43
-- Versão do servidor: 10.4.32-MariaDB
-- Versão do PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `sistemasdistribuidos`
--

-- --------------------------------------------------------

--
-- Estrutura para tabela `candidato`
--

CREATE TABLE `candidato` (
  `idCandidato` int(11) NOT NULL,
  `nome` varchar(30) NOT NULL,
  `email` varchar(50) NOT NULL,
  `senha` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `candidatocompetencia`
--

CREATE TABLE `candidatocompetencia` (
  `idCandidatoCompetencia` int(11) NOT NULL,
  `idCandidato` int(11) NOT NULL,
  `idCompetencia` int(11) NOT NULL,
  `tempo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `candidatovaga`
--

CREATE TABLE `candidatovaga` (
  `idCandidatoVaga` int(11) NOT NULL,
  `idCandidato` int(11) NOT NULL,
  `idVaga` int(11) NOT NULL,
  `visualizou` tinyint(1) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `competencia`
--

CREATE TABLE `competencia` (
  `idCompetencia` int(11) NOT NULL,
  `competencia` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `empresa`
--

CREATE TABLE `empresa` (
  `idEmpresa` int(11) NOT NULL,
  `razaoSocial` varchar(30) DEFAULT NULL,
  `email` varchar(50) NOT NULL,
  `senha` int(11) NOT NULL,
  `cnpj` int(11) NOT NULL,
  `ramo` varchar(255) DEFAULT NULL,
  `descricao` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `vaga`
--

CREATE TABLE `vaga` (
  `idVaga` int(11) NOT NULL,
  `idEmpresa` int(11) NOT NULL,
  `faixaSalarial` double DEFAULT NULL,
  `descricao` text DEFAULT NULL,
  `status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `vagacompetencia`
--

CREATE TABLE `vagacompetencia` (
  `idVagaCompetencia` int(11) NOT NULL,
  `idVaga` int(11) NOT NULL,
  `idCompetencia` int(11) NOT NULL,
  `tempo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Índices para tabelas despejadas
--

--
-- Índices de tabela `candidato`
--
ALTER TABLE `candidato`
  ADD PRIMARY KEY (`idCandidato`);

--
-- Índices de tabela `candidatocompetencia`
--
ALTER TABLE `candidatocompetencia`
  ADD PRIMARY KEY (`idCandidatoCompetencia`),
  ADD KEY `idCandidato` (`idCandidato`),
  ADD KEY `idCompetencia` (`idCompetencia`);

--
-- Índices de tabela `candidatovaga`
--
ALTER TABLE `candidatovaga`
  ADD PRIMARY KEY (`idCandidatoVaga`),
  ADD KEY `idCandidato` (`idCandidato`),
  ADD KEY `idVaga` (`idVaga`);

--
-- Índices de tabela `competencia`
--
ALTER TABLE `competencia`
  ADD PRIMARY KEY (`idCompetencia`);

--
-- Índices de tabela `empresa`
--
ALTER TABLE `empresa`
  ADD PRIMARY KEY (`idEmpresa`);

--
-- Índices de tabela `vaga`
--
ALTER TABLE `vaga`
  ADD PRIMARY KEY (`idVaga`),
  ADD KEY `idEmpresa` (`idEmpresa`);

--
-- Índices de tabela `vagacompetencia`
--
ALTER TABLE `vagacompetencia`
  ADD PRIMARY KEY (`idVagaCompetencia`),
  ADD KEY `idVaga` (`idVaga`),
  ADD KEY `idCompetencia` (`idCompetencia`);

--
-- AUTO_INCREMENT para tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `candidato`
--
ALTER TABLE `candidato`
  MODIFY `idCandidato` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `candidatocompetencia`
--
ALTER TABLE `candidatocompetencia`
  MODIFY `idCandidatoCompetencia` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `candidatovaga`
--
ALTER TABLE `candidatovaga`
  MODIFY `idCandidatoVaga` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `competencia`
--
ALTER TABLE `competencia`
  MODIFY `idCompetencia` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `empresa`
--
ALTER TABLE `empresa`
  MODIFY `idEmpresa` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `vaga`
--
ALTER TABLE `vaga`
  MODIFY `idVaga` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `vagacompetencia`
--
ALTER TABLE `vagacompetencia`
  MODIFY `idVagaCompetencia` int(11) NOT NULL AUTO_INCREMENT;

--
-- Restrições para tabelas despejadas
--

--
-- Restrições para tabelas `candidatocompetencia`
--
ALTER TABLE `candidatocompetencia`
  ADD CONSTRAINT `candidatocompetencia_ibfk_1` FOREIGN KEY (`idCandidato`) REFERENCES `candidato` (`idCandidato`),
  ADD CONSTRAINT `candidatocompetencia_ibfk_2` FOREIGN KEY (`idCompetencia`) REFERENCES `competencia` (`idCompetencia`);

--
-- Restrições para tabelas `candidatovaga`
--
ALTER TABLE `candidatovaga`
  ADD CONSTRAINT `candidatovaga_ibfk_1` FOREIGN KEY (`idCandidato`) REFERENCES `candidato` (`idCandidato`),
  ADD CONSTRAINT `candidatovaga_ibfk_2` FOREIGN KEY (`idVaga`) REFERENCES `vaga` (`idVaga`);

--
-- Restrições para tabelas `vaga`
--
ALTER TABLE `vaga`
  ADD CONSTRAINT `vaga_ibfk_1` FOREIGN KEY (`idEmpresa`) REFERENCES `empresa` (`idEmpresa`);

--
-- Restrições para tabelas `vagacompetencia`
--
ALTER TABLE `vagacompetencia`
  ADD CONSTRAINT `vagacompetencia_ibfk_1` FOREIGN KEY (`idVaga`) REFERENCES `vaga` (`idVaga`),
  ADD CONSTRAINT `vagacompetencia_ibfk_2` FOREIGN KEY (`idCompetencia`) REFERENCES `competencia` (`idCompetencia`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
