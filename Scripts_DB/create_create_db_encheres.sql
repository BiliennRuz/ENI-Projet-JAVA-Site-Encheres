USE [encheres]
GO
/****** Object:  User [utilisateurEncheres]    Script Date: 21/04/2022 16:21:27 ******/
CREATE USER [utilisateurEncheres] FOR LOGIN [utilisateurEncheres] WITH DEFAULT_SCHEMA=[dbo]
GO
ALTER ROLE [db_datareader] ADD MEMBER [utilisateurEncheres]
GO
ALTER ROLE [db_datawriter] ADD MEMBER [utilisateurEncheres]
GO
/****** Object:  Table [dbo].[ARTICLES_VENDUS]    Script Date: 21/04/2022 16:21:27 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ARTICLES_VENDUS](
	[no_article] [int] IDENTITY(1,1) NOT NULL,
	[nom_article] [varchar](30) NOT NULL,
	[description] [varchar](300) NOT NULL,
	[date_debut_encheres] [date] NOT NULL,
	[date_fin_encheres] [date] NOT NULL,
	[prix_initial] [int] NULL,
	[prix_vente] [int] NULL,
	[no_utilisateur] [int] NOT NULL,
	[no_categorie] [int] NOT NULL,
 CONSTRAINT [articles_vendus_pk] PRIMARY KEY CLUSTERED 
(
	[no_article] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CATEGORIES]    Script Date: 21/04/2022 16:21:27 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CATEGORIES](
	[no_categorie] [int] IDENTITY(1,1) NOT NULL,
	[libelle] [varchar](30) NOT NULL,
 CONSTRAINT [categorie_pk] PRIMARY KEY CLUSTERED 
(
	[no_categorie] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ENCHERES]    Script Date: 21/04/2022 16:21:27 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ENCHERES](
	[no_enchere] [int] IDENTITY(1,1) NOT NULL,
	[date_enchere] [datetime] NOT NULL,
	[montant_enchere] [int] NOT NULL,
	[no_article] [int] NOT NULL,
	[no_utilisateur] [int] NOT NULL,
 CONSTRAINT [enchere_pk] PRIMARY KEY CLUSTERED 
(
	[no_enchere] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[RETRAITS]    Script Date: 21/04/2022 16:21:27 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[RETRAITS](
	[no_article] [int] NOT NULL,
	[rue] [varchar](30) NOT NULL,
	[code_postal] [varchar](15) NOT NULL,
	[ville] [varchar](30) NOT NULL,
 CONSTRAINT [retrait_pk] PRIMARY KEY CLUSTERED 
(
	[no_article] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[UTILISATEURS]    Script Date: 21/04/2022 16:21:27 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[UTILISATEURS](
	[no_utilisateur] [int] IDENTITY(1,1) NOT NULL,
	[pseudo] [varchar](30) NOT NULL,
	[nom] [varchar](30) NOT NULL,
	[prenom] [varchar](30) NOT NULL,
	[email] [varchar](50) NOT NULL,
	[telephone] [varchar](15) NULL,
	[rue] [varchar](30) NOT NULL,
	[code_postal] [varchar](10) NOT NULL,
	[ville] [varchar](50) NOT NULL,
	[mot_de_passe] [varchar](30) NOT NULL,
	[credit] [int] NOT NULL,
	[administrateur] [bit] NOT NULL,
 CONSTRAINT [utilisateur_pk] PRIMARY KEY CLUSTERED 
(
	[no_utilisateur] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[ARTICLES_VENDUS]  WITH CHECK ADD  CONSTRAINT [articles_vendus_categories_fk] FOREIGN KEY([no_categorie])
REFERENCES [dbo].[CATEGORIES] ([no_categorie])
GO
ALTER TABLE [dbo].[ARTICLES_VENDUS] CHECK CONSTRAINT [articles_vendus_categories_fk]
GO
ALTER TABLE [dbo].[ARTICLES_VENDUS]  WITH CHECK ADD  CONSTRAINT [ventes_utilisateur_fk] FOREIGN KEY([no_utilisateur])
REFERENCES [dbo].[UTILISATEURS] ([no_utilisateur])
GO
ALTER TABLE [dbo].[ARTICLES_VENDUS] CHECK CONSTRAINT [ventes_utilisateur_fk]
GO
ALTER TABLE [dbo].[ENCHERES]  WITH CHECK ADD  CONSTRAINT [encheres_no_article_fk] FOREIGN KEY([no_article])
REFERENCES [dbo].[ARTICLES_VENDUS] ([no_article])
GO
ALTER TABLE [dbo].[ENCHERES] CHECK CONSTRAINT [encheres_no_article_fk]
GO
ALTER TABLE [dbo].[ENCHERES]  WITH CHECK ADD  CONSTRAINT [encheres_utilisateur_fk] FOREIGN KEY([no_utilisateur])
REFERENCES [dbo].[UTILISATEURS] ([no_utilisateur])
GO
ALTER TABLE [dbo].[ENCHERES] CHECK CONSTRAINT [encheres_utilisateur_fk]
GO
ALTER TABLE [dbo].[RETRAITS]  WITH CHECK ADD  CONSTRAINT [retrait_article_fk] FOREIGN KEY([no_article])
REFERENCES [dbo].[ARTICLES_VENDUS] ([no_article])
GO
ALTER TABLE [dbo].[RETRAITS] CHECK CONSTRAINT [retrait_article_fk]
GO
