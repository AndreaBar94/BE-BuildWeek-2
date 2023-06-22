import "bootstrap/dist/css/bootstrap.min.css";
import './App.css';
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Login from "./components/Login";
import Profile from "./components/Profile";
import Navbar from "./components/Navbar";

function App() {
  return (
    <>
    <BrowserRouter>
    <Navbar></Navbar>
			<Routes>
				<Route path="/profile" element={<Profile/>} />
			</Routes>
		</BrowserRouter>
    </>
  );
}

export default App;
