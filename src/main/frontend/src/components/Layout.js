import { Outlet, Link } from "react-router-dom";

const Layout = () => {
    return (
        <div>
            <header className="p-3 bg-dark text-white">
                <div className="container">
                    <div
                        className="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
                        <ul className="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                            <li><a href="/" className="nav-link px-2 text-white">Home</a></li>

                        </ul>

                        <div className="text-end">

                        </div>
                    </div>
                </div>
            </header>

            <main className="my-3">
                <div className="container">
                    <Outlet /> {/* Тут будут рендериться страницы */}
                </div>
            </main>

            <div className="container">
                <footer className="py-3 my-4 border-top">
                    <p className="text-center text-muted">@2025 Rental Motorcycle Company, Inc</p>
                </footer>
            </div>
        </div>
    );
};

export default Layout;
