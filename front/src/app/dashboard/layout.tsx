import { Metadata } from "next";
import FeedPage from "./_components/feed-page";
import Footer from "./_components/footer";
import { NavBar } from "./_components/nav-bar";

export const metadata: Metadata = {
    title: "Goober-Início",
    description: "Inicio da pagina goober",
  };

const dashboardLayout = ({
    children,
}: {
    children: React.ReactNode;
}) => {

    return (

        <div>
            <NavBar/>  
            {children}
        </div>

    );
};

export default dashboardLayout;