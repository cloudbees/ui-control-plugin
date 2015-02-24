[See User Story #630](https://cloudbees.tpondemand.com/entity/630) for the context of this project.
 

# Experiments
## Create a "New Jelly style DOM generated component"
Effort:
- [x] "Path browser" component that was originally developed for JOC was refactored into a reusable component
- [x] Mostly removed JOC-ness in the control and generalized it
- [ ] Make sure there's no loss of functionality
- [ ] Remove any remaining JOC-ness in the control.
- [ ] Import JavaScript libraries through adjunct framework instead of bundling it locally

Result:
- Proved that it is feasible to create such components
- Designing reusable component requires a help from a Java guy

Open questions:
- Can we maintain backward compatibility with reasonable effort? What if libraries upgrade?
- Any easy tests that can be written?

## Client-side MVC framework
TBD