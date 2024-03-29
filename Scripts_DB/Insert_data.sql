USE [encheres]
GO
SET IDENTITY_INSERT [dbo].[CATEGORIES] ON 

INSERT [dbo].[CATEGORIES] ([no_categorie], [libelle]) VALUES (1, N'Informatique')
INSERT [dbo].[CATEGORIES] ([no_categorie], [libelle]) VALUES (2, N'Ameublement')
INSERT [dbo].[CATEGORIES] ([no_categorie], [libelle]) VALUES (3, N'Vetement')
INSERT [dbo].[CATEGORIES] ([no_categorie], [libelle]) VALUES (4, N'Sport & Loisirs')
SET IDENTITY_INSERT [dbo].[CATEGORIES] OFF
SET IDENTITY_INSERT [dbo].[UTILISATEURS] ON 

INSERT [dbo].[UTILISATEURS] ([no_utilisateur], [pseudo], [nom], [prenom], [email], [telephone], [rue], [code_postal], [ville], [mot_de_passe], [credit], [administrateur]) VALUES (5, N'Dom', N'AMPS', N'Dominique', N'dominique.amps2022@campus-eni.fr', N'0678628248', N'Moguerou', N'29190', N'GOUEZEC', N'toto', 0, 0)
INSERT [dbo].[UTILISATEURS] ([no_utilisateur], [pseudo], [nom], [prenom], [email], [telephone], [rue], [code_postal], [ville], [mot_de_passe], [credit], [administrateur]) VALUES (10, N'Yann', N'Biheul', N'Yannick', N'yannickbiheul@outlook.fr', N'0123456789', N'Keradennec', N'29000', N'Quimper', N'123456', 100, 0)
INSERT [dbo].[UTILISATEURS] ([no_utilisateur], [pseudo], [nom], [prenom], [email], [telephone], [rue], [code_postal], [ville], [mot_de_passe], [credit], [administrateur]) VALUES (11, N'jer', N'Vasseur', N'JÃ©rÃ´me', N'jerome.vasseur2022@campus-eni.fr', N'0654454587', N'rue', N'25647', N'ville', N'jer', 100, 0)
INSERT [dbo].[UTILISATEURS] ([no_utilisateur], [pseudo], [nom], [prenom], [email], [telephone], [rue], [code_postal], [ville], [mot_de_passe], [credit], [administrateur]) VALUES (12, N'TerryG', N'Gilliam', N'Terry', N'terrygilliam@gmail.com', N'0123456987', N'Keradennec', N'29000', N'Quimper', N'123456', 100, 0)
SET IDENTITY_INSERT [dbo].[UTILISATEURS] OFF
SET IDENTITY_INSERT [dbo].[ARTICLES_VENDUS] ON 

INSERT [dbo].[ARTICLES_VENDUS] ([no_article], [nom_article], [description], [date_debut_encheres], [date_fin_encheres], [prix_initial], [prix_vente], [no_utilisateur], [no_categorie], [status_vente]) VALUES (1, N'Clavier', N'Clavier qwerty', CAST(N'2022-04-14' AS Date), CAST(N'2022-04-22' AS Date), 50, 0, 5, 1, N'Vente non débuté')
INSERT [dbo].[ARTICLES_VENDUS] ([no_article], [nom_article], [description], [date_debut_encheres], [date_fin_encheres], [prix_initial], [prix_vente], [no_utilisateur], [no_categorie], [status_vente]) VALUES (2, N'Ecran', N'ecran 4k 23"', CAST(N'2022-04-14' AS Date), CAST(N'2022-04-22' AS Date), 100, NULL, 5, 1, N'Vente non débuté')
SET IDENTITY_INSERT [dbo].[ARTICLES_VENDUS] OFF
