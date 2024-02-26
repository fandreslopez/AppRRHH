import { BrowserRouter, Route, Routes } from "react-router-dom";
import ListadoEmpleados from "./Empleados/ListadoEmpleados";
import Navegacion from "./plantilla/navegacion";
import AgregarEmpleado from "./Empleados/AgregarEmpleado";
import EditarEmpleado from "./Empleados/EditarEmpleado";

function App() {
  return (
    <div className="container">
      <BrowserRouter>
        <Navegacion />
        <Routes>
          <Route exact path="/" element={<ListadoEmpleados />} />
          <Route exact path="/agregar" element={<AgregarEmpleado />} />
          <Route exact path="/editar/:id" element={<EditarEmpleado />} />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
