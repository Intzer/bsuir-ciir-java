import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';

const Header = () => {
    const [isLogged, setIsLogged] = useState(false);

    useEffect(() => {
        const loggedStatus = localStorage.getItem('isLogged') === 'true'; // Проверка авторизации
        setIsLogged(loggedStatus);
    }, []);

    return (
        <header className="p-3 bg-dark text-white">
            <div className="container">
                <div className="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
                    <ul className="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                        <li><Link to="/" className="nav-link px-2 text-white">Home</Link></li>

                        {isLogged && (
                            <>
                                <li><Link to="/rent" className="nav-link px-2 text-white">Rent</Link></li>
                                <li><Link to="/active" className="nav-link px-2 text-white">Active rents</Link></li>
                                <li><Link to="/history" className="nav-link px-2 text-white">History</Link></li>
                            </>
                        )}
                    </ul>

                    <div className="text-end">
                        {isLogged ? (
                            <div className="d-flex align-items-center">
                                <Link to="/cabinet" className="btn btn-light me-2">Cabinet</Link>
                                <Link to="/logout" className="btn btn-danger">Log out</Link>
                            </div>
                        ) : (
                            <Link to="/auth" className="btn btn-light">Sign in</Link>
                        )}
                    </div>
                </div>
            </div>
        </header>
    );
};

export default Header;
