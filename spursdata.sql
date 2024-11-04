-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost
-- Généré le : mar. 08 oct. 2024 à 15:20
-- Version du serveur : 11.4.2-MariaDB
-- Version de PHP : 8.3.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `spursdata`
--

-- --------------------------------------------------------

--
-- Structure de la table `joueurs`
--

CREATE TABLE `joueurs` (
  `nom` varchar(15) NOT NULL,
  `prénom` varchar(15) NOT NULL,
  `age` int(11) NOT NULL,
  `poid` int(11) NOT NULL,
  `taille` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `joueurs`
--

INSERT INTO `joueurs` (`nom`, `prénom`, `age`, `poid`, `taille`) VALUES
('Collins', 'Zach', 25, 113, 211),
('Johnson', 'Keldon', 24, 100, 196),
('Sochan', 'Jeremy', 20, 107, 206),
('Vassell', 'Devin', 23, 91, 196),
('Wembanyama', 'Victor', 19, 95, 224);

-- --------------------------------------------------------

--
-- Structure de la table `matchs`
--

CREATE TABLE `matchs` (
  `MatchID` int(11) NOT NULL,
  `Adversaire` varchar(10) NOT NULL,
  `Lieu` varchar(10) NOT NULL,
  `Resultat` varchar(10) NOT NULL,
  `DifScore` varchar(4) NOT NULL,
  `Date` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `matchs`
--

INSERT INTO `matchs` (`MatchID`, `Adversaire`, `Lieu`, `Resultat`, `DifScore`, `Date`) VALUES
(1, 'Thunder', 'Extérieur', 'Défaite', '-1', '2023-10-10'),
(2, 'Heat', 'Extérieur', 'Victoire', '+16', '2023-10-14'),
(3, 'Rockets', 'Domicile', 'Victoire', '+14', '2023-10-19');

-- --------------------------------------------------------

--
-- Structure de la table `statcards`
--

CREATE TABLE `statcards` (
  `Nom` varchar(15) NOT NULL,
  `MatchID` int(11) NOT NULL,
  `TDJeu` varchar(6) NOT NULL,
  `Points` varchar(4) NOT NULL,
  `Rebonds` int(11) NOT NULL,
  `Passe_D` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `statcards`
--

INSERT INTO `statcards` (`Nom`, `MatchID`, `TDJeu`, `Points`, `Rebonds`, `Passe_D`) VALUES
('Collins', 1, '22:44', '15', 5, 5);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `joueurs`
--
ALTER TABLE `joueurs`
  ADD PRIMARY KEY (`nom`);

--
-- Index pour la table `matchs`
--
ALTER TABLE `matchs`
  ADD PRIMARY KEY (`MatchID`);

--
-- Index pour la table `statcards`
--
ALTER TABLE `statcards`
  ADD KEY `FK_MatchID` (`MatchID`),
  ADD KEY `FK_Nom` (`Nom`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `matchs`
--
ALTER TABLE `matchs`
  MODIFY `MatchID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `statcards`
--
ALTER TABLE `statcards`
  ADD CONSTRAINT `FK_MatchID` FOREIGN KEY (`MatchID`) REFERENCES `matchs` (`MatchID`),
  ADD CONSTRAINT `FK_Nom` FOREIGN KEY (`Nom`) REFERENCES `joueurs` (`nom`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
