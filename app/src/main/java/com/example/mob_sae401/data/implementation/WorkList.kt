package com.example.mob_sae401.data.implementation

import com.example.mob_sae401.R
import com.example.mob_sae401.data.models.Review
import com.example.mob_sae401.data.models.Work
import com.example.mob_sae401.data.models.WorkType

object WorkList {
    val list: List<Work> = listOf(
        Work(
            1,
            "Jurassic Park",
            "Jurassic Park est un film d'aventure réalisé par Steven Spielberg. Il raconte l'histoire d'un parc à thème où des dinosaures clonés sont ramenés à la vie grâce à des avancées scientifiques. Lorsque les systèmes de sécurité du parc échouent, les visiteurs doivent se battre pour leur survie contre ces créatures préhistoriques. Ce film a marqué le début d'une franchise de plusieurs films et est connu pour ses effets spéciaux révolutionnaires et sa musique emblématique composée par John Williams.",
            "Steven Spielberg",
            "1993",
            false,
            R.drawable.jurassic,
            emptyList(),
            WorkType.MOVIE
        ),
        Work(
            2,
            "Inception",
            "Inception est un thriller de science-fiction réalisé par Christopher Nolan. Le film suit Dom Cobb, un voleur spécialisé dans l'extraction d'informations sensibles des rêves de ses cibles. Cobb est engagé pour une mission encore plus complexe : implanter une idée dans l'esprit de quelqu'un, un processus appelé \"inception\". Le film explore les profondeurs du subconscient humain à travers une série de rêves imbriqués, créant un monde visuel fascinant et complexe. Inception est acclamé pour son scénario innovant, ses effets spéciaux époustouflants et sa musique hypnotique composée par Hans Zimmer.",
            "Christopher Nolan",
            "2010",
            false,
            R.drawable.inception,
            listOf(
                Review(
                    6,
                    "Utilisateur 6",
                    "Des scènes d'action à couper le souffle et une histoire palpitante!",
                    3.3f
                )
            ),
            WorkType.MOVIE
        ),
        Work(
            3,
            "The Wolf of Wall Street",
            "The Wolf of Wall Street est un film biographique réalisé par Martin Scorsese. Il est basé sur l'histoire vraie de Jordan Belfort, un courtier en bourse de New York dont l'entreprise, Stratton Oakmont, a pratiqué des fraudes massives. Le film dépeint la montée fulgurante et la chute vertigineuse de Belfort, ainsi que ses excès en matière de drogue, de sexe et de pouvoir. The Wolf of Wall Street est connu pour son ton provocateur et son interprétation charismatique par Leonardo DiCaprio dans le rôle principal. Il offre une critique acerbe du capitalisme et de la corruption financière.",
            "Martin Scorsese",
            "2013",
            true,
            R.drawable.wallstreet,
            emptyList(),
            WorkType.MOVIE
        ),
        Work(
            4,
            "Le Fabuleux Destin d'Amélie Poulain",
            "Le Fabuleux Destin d'Amélie Poulain est une comédie romantique fantaisiste réalisée par Jean-Pierre Jeunet. Le film raconte l'histoire d'Amélie, une jeune femme timide mais imaginative vivant à Montmartre. Après avoir trouvé une boîte à souvenirs cachée dans son appartement, Amélie décide de changer la vie de ceux qui l'entourent de manière subtile et bienveillante. Le film est célèbre pour son esthétique colorée et poétique, sa musique envoûtante composée par Yann Tiersen, et la performance charmante d'Audrey Tautou dans le rôle principal.",
            "Jean-Pierre Jeunet",
            "2001",
            false,
            R.drawable.amelie,
            listOf(
                Review(
                    2,
                    "Utilisateur 2",
                    "Une histoire charmante et poétique qui m'a vraiment touché.",
                    4.9f
                ),
                Review(7, "Utilisateur 7", "Un conte poétique et philosophique.", 4.2f)
            ),
            WorkType.MOVIE
        ),
        Work(
            5,
            "Pulp Fiction",
            "Pulp Fiction est un film culte réalisé par Quentin Tarantino. Il se compose de plusieurs récits entrecroisés impliquant des personnages comme des gangsters, un boxeur, et une femme fatale. Le film est connu pour son dialogue vif, son humour noir, et sa structure narrative non linéaire. John Travolta, Samuel L. Jackson, et Uma Thurman offrent des performances mémorables. Pulp Fiction a revitalisé la carrière de Travolta et a cimenté Tarantino comme l\'un des réalisateurs les plus influents de sa génération.",
            "Quentin Tarantino",
            "1994",
            false,
            R.drawable.pulp,
            emptyList(),
            WorkType.MOVIE
        ),
        Work(
            6,
            "Harry Potter à l'école des sorciers",
            "Harry Potter à l'école des sorciers est le premier livre de la série Harry Potter, écrit par J.K. Rowling. Il raconte l'histoire d'un jeune sorcier nommé Harry Potter et de ses aventures à l'école de sorcellerie de Poudlard. Là, Harry découvre qu'il est le survivant d'une attaque meurtrière par le sorcier maléfique Voldemort. Le livre suit Harry alors qu'il apprend la magie, se fait des amis, et affronte le mal qui menace le monde sorcier. Avec son mélange de mystère, de magie, et d'amitié, Harry Potter à l'école des sorciers a captivé des millions de lecteurs à travers le monde.",
            "J.K. Rowling",
            "1997",
            false,
            R.drawable.harry_potter,
            emptyList(),
            WorkType.BOOK
        ),
        Work(
            7,
            "Le Petit Prince",
            "Le Petit Prince est un conte philosophique et poétique, qui raconte les aventures d'un jeune prince venu d'un autre monde. Écrit par Antoine de Saint-Exupéry, le livre explore des thèmes de l'amour, de la perte, de l'amitié, et de la beauté de la vie à travers les yeux innocents du petit prince. Chaque rencontre du prince est une allégorie et offre une leçon de vie aux lecteurs de tous âges.",
            "Antoine de Saint-Exupéry",
            "1943",
            false,
            R.drawable.petitprince,
            emptyList(),
            WorkType.BOOK
        ),
        Work(
            8,
            "Les Misérables",
            "Les Misérables, écrit par Victor Hugo, est un roman historique qui se déroule dans la France du XIXe siècle. Il suit les vies entrecroisées de plusieurs personnages, notamment Jean Valjean, un ancien forçat, et l'inspecteur Javert, qui le poursuit sans relâche. Le roman explore des thèmes de justice, de rédemption, de lutte contre la pauvreté et de sacrifice.",
            "Victor Hugo",
            "1862",
            false,
            R.drawable.miserable,
            emptyList(),
            WorkType.BOOK
        ),
        Work(
            9,
            "Le Chardonneret",
            "Le Chardonneret, écrit par Donna Tartt, est un roman qui suit la vie de Theo Decker, un garçon de treize ans dont la mère meurt dans un attentat au Metropolitan Museum of Art. Le roman explore ses luttes avec la perte, la culpabilité et la dépression, ainsi que son lien avec un tableau célèbre, 'Le Chardonneret', qu'il a sauvé de l'attentat.",
            "Donna Tartt",
            "2013",
            false,
            R.drawable.chardonnet,
            emptyList(),
            WorkType.BOOK
        ),
        Work(
            10,
            "Le Parfum",
            "Le Parfum, écrit par Patrick Süskind, raconte l'histoire de Jean-Baptiste Grenouille, un homme né avec un sens de l'odorat exceptionnel. Grenouille devient obsédé par la création du parfum parfait, allant jusqu'à commettre des meurtres pour obtenir les essences nécessaires. Le roman explore des thèmes de l'obsession, de la beauté et de la dépravation.",
            "Patrick Süskind",
            "1985",
            false,
            R.drawable.leparfum,
            emptyList(),
            WorkType.BOOK
        )
    )
}