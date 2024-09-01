import { NavBar } from "./_components/nav-bar";

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