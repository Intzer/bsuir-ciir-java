import { BrowserRouter, Routes, Route } from "react-router-dom";
import { AuthProvider } from "./AuthContext";  // Импортируем контекст
import Layout from "./components/Layout";
import Home from "./pages/Home";
import Auth from "./pages/Auth";
import Code from "./pages/Code";
import Cabinet from "./pages/Cabinet";
import History from "./pages/History";
import Active from "./pages/Active";
import Rent from "./pages/Rent";

function App() {
    return (
        <AuthProvider>
            <BrowserRouter>
                <Routes>
                    <Route path="/" element={<Layout />}>
                        <Route index element={<Home />} />
                        <Route path="auth" element={<Auth />} />
                        <Route path="code" element={<Code />} />
                        <Route path="cabinet" element={<Cabinet />} />
                        <Route path="history" element={<History />} />
                        <Route path="active" element={<Active />} />
                        <Route path="rent" element={<Rent />} />
                    </Route>
                </Routes>
            </BrowserRouter>
        </AuthProvider>
    );
}

export default App;
