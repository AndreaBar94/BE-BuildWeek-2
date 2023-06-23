import Navbar from "./Navbar";

const Home = () => {
    return(
        <>
        <Navbar/>
        <div className="d-flex justify-content-around my-5">
        <section className="mx-5">
            <div className="fs-5">Fatture</div>
        </section>

        <section className="mx-5">
            <div className="fs-5">Utenti</div>
        </section>

        <section className="mx-5">
            <div className="fs-5">Clienti</div>
        </section>
        </div>
       
        </>
    )
}

export default Home;