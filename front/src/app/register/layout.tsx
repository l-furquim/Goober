import { Metadata } from "next";
import RegisterPage from "./page";

export const metadata: Metadata = {
    title: "Goober-Registro",
    description: "Inicio da pagina goober",
  };



export default function RegisterLayout(){
    return (
        <RegisterPage/>
    )
}