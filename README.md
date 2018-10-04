
# Progetto    : "PlatoS"

## Introduzione 


Con il passare degli anni lo sviluppo su piattaforma mobile è andato man mano crescendo. Tale
crescita è avvenuta in molteplici versi, sia come avanzamento hardware che dal punto di vista
applicativo. L’evoluzione che tendiamo a notare è l’aumento della potenza grafica, dimensione degli
schermi e incremento di risoluzione. Un così grande sviluppo dal punto di vista grafico lascia la
possibilità a numerosi sviluppatori, sia professionisti che amatoriali, libero arbitrio nella
progettazione di applicazioni mobile.
Le grandi aziende ormai producono un così grande numero di smartphone che per un
programmatore adattare un’interfaccia a tutte le tipologie di device su mercato può essere davvero
complesso.
Durante lo sviluppo di un’applicazione mobile, numerosi programmatori sono costretti a realizzare
unicamente mockup o sketch dell’interfaccia. Successivamente, nelle seguenti fasi di sviluppo, le
interfacce utente vengono completate con la logica applicativa. Ma cosa può accadere se dopo ore
ed ore di sviluppo emergono nei test diverse problematiche di usabilità? Bisogna riprendere tutto il
progetto in mano e riprogettare di nuovo sia l’interfaccia utente che la logica applicativa
dell’applicazione. Un così ingente lavoro può richiedere dei costi di sviluppo enormi, così sprecando
del tempo e risorse che potrebbero essere utilizzate per il completamento del progetto stesso.
È spesso semplice realizzare la logica che vi è dietro un’applicazione ma può risultare molto più
oneroso il processo di disegno di una interfaccia utente “usabile”. La definizione di usabile è
piuttosto generica. Come possiamo verificare se un’interfaccia è usabile? Il che l’utente “riesca” a
concludere operazioni con essa, non implica dire che non vi siano state difficoltà nell’interazione.
Un’interfaccia usabile dovrebbe essere in grado di aiutare l’utente a concludere un compito per cui
è progettata nel minor tempo possibile non intralciandolo. Quindi può un’interfaccia rallentare
l’utente? Cosa succede se i componenti sono troppo vicini o troppo lontani? Sono molti i fattori di
rischio che possono rendere un’interfaccia poco efficace (messaggi troppo ermetici, colori sviatori,
ecc.).
Durante lo sviluppo delle interfacce per applicazioni mobile, i test a campione hanno portato alla
scoperta di “elementi critici” o “zone critiche”. Possiamo definire questi come punti in cui l’utente
cerca di concludere un’operazione ma non riesce o viene rallentato. Le zone critiche possono essere
scoperte o pensate. Pensare una zona critica è creare una parte di un’interfaccia dove si pensa che
l’utente possa “perdersi”. Scoprire una zona critica è testare un’interfaccia pensata “usabile” e
veder emergere dei punti d’interesse dove l’utente “si perde”. Grazie ai test è possibile creare dei
canoni di sviluppo d’interfacce che sono determinanti alla costruzione di quella che si può definire
una “buona User Interface”. I test degli utenti, la scoperta di errori e l’analisi di queste zone hanno
portato alla costruzione di canoni di progettazione. Il Material Design di Google è un esempio. Ma
una verifica interessante è testare interfacce che seguono specifici canoni per verificare l’effettiva
usabilità.
La soluzione proposta per mitigare il problema della progettazione di interfacce utente usabili è lo
sviluppo di un’applicazione che sia in grado di simulare a runtime il funzionamento di un’interfaccia
non ancora implementata o completata con logica applicativa.
Sviluppare un’interfaccia ed in seguito implementarla corrisponde ad un buon ammonto di costi di
sviluppo. L’applicazione dovrà permettere allo sviluppatore di testare la propria interfaccia,
raccogliendo informazioni utili al miglioramento dell’interfaccia utente. Per questo motivo, durante
l’attività svolta è stato prodotto un framework (chiamato “PlatoS”) composto da una coppia di
applicazioni che cercano di risolvere queste problematiche. La prima applicazione in versione
desktop si occupa di fissare le regole di simulazione dell’interfaccia, mentre la seconda funge da
motore della simulazione raccogliendo dati in tempo reale. Le applicazioni sono state implementate
ed in seguito testate su un campione di persone di diverse età e con diverse competenze
tecnologiche. Ai partecipanti della valutazione è stato richiesto di testare diverse interfacce, alcune
con punti critici ed altre senza. I risultati hanno evidenziato come il sistema riesca in maniera
significativa ad evidenziare i punti critici delle interfacce, quando un test viene eseguito più volte da
diversi partecipanti. Quindi, tramite i test eseguiti attraverso l’uso software sviluppato, è stato
possibile verificare l’usabilità delle interfacce mockup.
PlatoS, ovvero Plato Software deriva dal latino “plato” ovvero platonico. Platone sosteneva che
esistessero due mondi, uno ideale ed uno materiale. Qualsiasi cosa noi progettiamo nel nostro
mondo ideale è perfetto, ma tale perfezione non può essere proiettata nel mondo materiale. Tutto
ciò che creiamo potrà essere solo un’imitazione della perfezione realizzabile idealmente. Stesso
discorso vale per le interfacce utente, per quanto ci sforziamo a pensare che esse siano perfette o
usabili è solo qualcosa che idealmente è concretizzabile finché esse non si scontrano con i test,
ovvero il nostro mondo materiale.


