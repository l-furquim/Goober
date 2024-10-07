import { Metadata } from "next/types";
import LoginPage from "./page";

export const metadata: Metadata = {
    title: "Goober-Login",
    description: "Inicio da pagina goober",
  };



export default function LoginLayout () {
    return (
        <LoginPage/>
    )
}