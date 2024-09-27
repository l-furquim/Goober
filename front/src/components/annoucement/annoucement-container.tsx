

import { AnnouncementProps, GetAllAnnouncementsResponse } from "@/app/dashboard/_components/feed-page";
import { Link } from "lucide-react";

export type AnnouncementsList = {
    announcements: AnnouncementProps[]
}

const AnnouncementContainer : React.FC<AnnouncementsList> = ({ announcements }) => {

    return(
        <h1></h1>
    )
}
export default AnnouncementContainer;