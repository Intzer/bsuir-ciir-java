import { Outlet, Link } from "react-router-dom";
import Header from "./Header";
import Footer from "./Footer";

const Layout = () => {
    return (
        <div>
            <Header />

            <main className="my-3">
                <div className="container">
                    <Outlet />
                </div>
            </main>

            <Footer />
        </div>
    );
};

export default Layout;
