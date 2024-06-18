import moment from 'moment';
import Tooltip from 'react-bootstrap/Tooltip';
import OverlayTrigger from 'react-bootstrap/OverlayTrigger';

function getFormattedDescription(description) {
    const MAX_LENGTH_DESCRIPTION = 60;
    const substr = description.substring(0, MAX_LENGTH_DESCRIPTION);

    let formatted = substr;
    if (description.length !== substr.length) formatted += '...';

    return formatted;
}

function getFormattedDate(date_dd_mm_yyyy) {
    
    const date = moment(date_dd_mm_yyyy, 'DD/MM/YYYY');
    const formattedDate = date.format('MMM DD')

    return formattedDate;
}

function getNameInitials(name) {
    const names = name.trim().split(' ');
    const first = names.at(0);
    const last  = names.at(-1);

    return first[0] + ' ' + last[0];
}

function prepareUsernames(usernames) {
    const LIMIT = 4;
    const LENGTH = usernames.length;
    const REMAINING = LENGTH - LIMIT; 

    const first_usernames = usernames.slice(0, LIMIT).map((username) => (
        {
            name: username,
            initials: getNameInitials(username)
        }
    ));

    if (REMAINING <= 0) return first_usernames;

    first_usernames.pop();
    const str_remaining_usernames = usernames.slice(LIMIT-1, ).reduce((acc, username) => acc + "<br/>" + username, "");
    const remaining_usernames = {
        name: str_remaining_usernames,
        initials: `+${REMAINING + 1}`
    }
    
    return [...first_usernames, remaining_usernames];
}
  
const Task = ({ task }) => {
    const { id, description, usersNames, endDate, subtask } = task;

    return (
        <div className="tf-task rounded user-select-none" data-id={id}>
            { subtask ? <i class="tf-task-marker bi bi-bookmark"></i> : <i class="tf-task-marker bi bi-bookmark-star-fill"></i> }

            <div className="tf-task-header">
                <span className="tf-task-header--id">#{id}</span> <span className="tf-task-header--title">{task.title}</span>
            </div>
            
            <div className="tf-task-description">{getFormattedDescription(description)}</div>
            <div className='tf-task-info'>
                <div className='tf-task-date'>
                    <span className="bi bi-clock"></span>
                    <span>{getFormattedDate(endDate)}</span>
                </div>

                <div className="tf-task-users">
                    {prepareUsernames(usersNames).map(({name, initials}, index) => (
                        <OverlayTrigger key={index} delay={{ hide: 150, show: 150 }} overlay={(props) => (
                          <Tooltip {...props} >{name}</Tooltip>
                        )} placement="bottom">
                            <div className="tf-task-user"><span className="tf-task-user-initials">{initials}</span></div>
                      </OverlayTrigger>
                    ))}
                </div>
            </div>
            
        </div>
    )
};

export default Task;