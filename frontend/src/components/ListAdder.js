

export default function ListAdder({ previousListId = undefined, actions }) {

    return (
        <div className="tf-list-adder" onClick={() => actions.click(previousListId)}>
            <span className="tf-list-adder-icon">
                <i className="bi bi-plus-square-dotted"></i>
            </span>
        </div>
    )

}