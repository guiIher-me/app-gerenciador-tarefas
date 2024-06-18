import { Tooltip as BsTooltip } from "bootstrap"
import React, { useEffect, useRef } from "react"

const Tooltip = (p) => {
    const childRef = useRef();

    useEffect(() => {
        const t = new BsTooltip(childRef.current, {
            title: p.text,
            html: true,
            placement: "right",
            trigger: "hover"
        })
        return () => t.dispose()
    }, [p.text])

    return React.cloneElement(p.children, { ref: childRef })
}

export default Tooltip;