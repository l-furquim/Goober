import FeedPage from "./_components/feed-page";
import Footer from "./_components/footer";
import { NavBar } from "./_components/nav-bar";

const dashboardLayout = ({
    children,
}: {
    children: React.ReactNode;
}) => {

    return (

        <div>
            <NavBar/>
            <FeedPage/>    
            {children}
        </div>

    );
};

export default dashboardLayout;