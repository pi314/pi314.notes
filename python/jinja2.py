from jinja2 import Environment, FileSystemLoader
loader = FileSystemLoader('templates')
env = Environment(loader=loader)

# get and render template file
template = env.get_template('presentation.html')
template.render(key=value)
